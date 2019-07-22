window.jcontrol = window.jcontrol || {};

(function($b) {

	$b.controls = null;
	$b.percentage = null;
	$b.idCoopac = null;
	$b.className = null;
	$b.size = null;

	$b.iniControls = function(idCoopac, className, size) {
		this.idCoopac = idCoopac;
		this.className = className;
		this.size = size;
		jajax.apiAuthGet({
			url : jbase.urls.control_coopac + "/" + idCoopac,
			success : function(data) {
				// INI:SUCCESS

				// --------------------
				// cargar los controles
				jcontrol.controls = data;

				// Update porcentaje en los gráficos
				jcontrol.updateDraw(idCoopac, className, size);
				// Control de los cambios en los objetos html
				$(".look-forward").on("input change", function() {
					var final = jcontrol.controls.filter(function(data) {
						return data.codControl == "P05_IS_FINAL";
					});

					if (!final || final.length <= 0)
						return;
					
					var that = $(this);
					var codControl = that.attr("control");
					// jnoty.information($(this).attr("control"));
					var obj = jcontrol.controls.filter(function(data) {
						//if (data.indActivo == 1)
							return data.codControl == codControl;
						//return null
					});
					if (!obj || obj.length <= 0)
						return;

					var val = that.val();
					if (val != undefined && val != null && val != ""){
						obj[0].indLlenado = 1;
					}
					else{
						obj[0].indLlenado = 0;
						if( obj[0].indActivo == 1 ){
							final[0].indLlenado = 0;
						}
					}
					
					
					if (that.is(":checkbox")){
						if(that.is(":checked")){
							obj[0].indLlenado = 1;
						}
						else{
							obj[0].indLlenado = 0;
							final[0].indLlenado = 0;
						}
					}

					// aplicar reglas de negocio
					jcontrol.rulerIn(that, codControl, obj[0].indLlenado, final);

					// actualizar
					jcontrol.updateDraw(idCoopac, className, size);
					
					
					//VALIDAR los pasos del 1 al 4
					if( $("#P01").hasClass("flow-step-success") &&
						$("#P02").hasClass("flow-step-success") &&
						$("#P03").hasClass("flow-step-success") &&
						$("#P04").hasClass("flow-step-success")
					  ){
						final[0].indLlenado = 1;
					}
					
					// actualizar
					jcontrol.updateDraw(idCoopac, className, size);
				});

				// FIN:SUCCESS
			}
		});

	};

	$b.updateDraw = function(idCoopac, className, size) {

		// ------------------------------
		// pintar el porcentaje de avance
		var total = jcontrol.controls.reduce(function(pivot, kv) {
			if (kv.indActivo == 1)
				return pivot + 1;
			return pivot;
		}, 0);

		var totalLlenado = jcontrol.controls.reduce(function(pivot, kv) {
			if (kv.indActivo == 1)
				return pivot + kv.indLlenado;
			return pivot;
		}, 0);
		var part = jbase.roundNumber(Math.floor(totalLlenado) / total, 2);
		jcontrol.percentage = part;
		jcontrol.draw(size, className, part);

		// --------------------------
		// pintar avance de cada paso
		var newMap = [];
		jcontrol.controls.map(function(kv) {
			if (!(kv.indActivo == 1))
				return;
			var pivot = kv.codPaso;
			var obj = newMap.filter(function(data) {
				return data.codPaso == pivot;
			});

			if (obj.length <= 0) {
				obj = {
					codPaso : kv.codPaso,
					total : 1,
					llenado : kv.indLlenado
				};
				newMap.push(obj);
			} else {
				obj[0].total += 1;
				obj[0].llenado += kv.indLlenado;
			}
		});

		if (newMap.length <= 0)
			return;

		newMap.map(function(kv) {
			var step = $("#" + kv.codPaso);
			if (step.length <= 0)
				return;
			step.removeClass("flow-step-incomplete");
			step.removeClass("flow-step-success");
			step.removeClass("flow-step-empty");
			if(kv.llenado === 0){
				if (!step.hasClass("flow-step-empty"))
					step.addClass("flow-step-empty");
			}
			else if (kv.total == kv.llenado) {
				if (!step.hasClass("flow-step-success"))
					step.addClass("flow-step-success");
			} else if (kv.llenado > 0) {
				if (!step.hasClass("flow-step-incomplete"))
					step.addClass("flow-step-incomplete");
			}
		});
	};

	$b.draw = function(size, className, percentage) {
		var pre_sectors = [ percentage, 1 - percentage ];
		if (percentage >= 1)
			pre_sectors = [ 0.99999, 0.000001 ];
		else if (percentage <= 0)
			pre_sectors = [ 0.000001, 0.99999 ];
		var color_ok = "#4C6298";
		var color_nook = "#EDF0F5";
		var colors = [ color_ok, color_nook ];// Ordernar
		var sectors = [];

		var l = size / 2
		var a = 0 // Angle
		var aRad = 0 // Angle in Rad
		var z = 0 // Size z
		var x = 0 // Side x
		var y = 0 // Side y
		var X = 0 // SVG X coordinate
		var Y = 0 // SVG Y coordinate
		var R = 0 // Rotation

		pre_sectors.map(function(percentage, key) {
			a = 360 * percentage;
			aCalc = (a > 180) ? 360 - a : a;
			aRad = aCalc * Math.PI / 180;
			z = Math.sqrt(2 * l * l - (2 * l * l * Math.cos(aRad)));
			if (aCalc <= 90) {
				x = l * Math.sin(aRad);
			} else {
				x = l * Math.sin((180 - aCalc) * Math.PI / 180);
			}

			y = Math.sqrt(z * z - x * x);
			if (isNaN(y))
				Y = 0;
			else
				Y = y;

			if (a <= 180) {
				X = l + x;
				arcSweep = 0;
			} else {
				X = l - x;
				arcSweep = 1;
			}

			sectors.push({
				percentage : percentage,
				// label : item.label,
				color : colors[key],
				arcSweep : arcSweep,
				L : l,
				X : X,
				Y : Y,
				R : R
			});

			R = R + a;
		});
		// ---
		var main = document.getElementsByClassName(className)[0]
		if (main && main.hasChildNodes())
			main.removeChild(main.childNodes[0]);
		// ---
		var newSVG = document.createElementNS("http://www.w3.org/2000/svg", "svg");
		newSVG.setAttributeNS(null, 'style', "width: " + size + "px; height: " + size + "px");
		newSVG.setAttributeNS(null, 'class', "sbs-piechart");
		newSVG.setAttributeNS(null, 'viewBox', "0 0 " + size + " " + size);
		main.appendChild(newSVG)

		sectors.map(function(sector) {

			var newSector = document.createElementNS("http://www.w3.org/2000/svg", "path");
			newSector.setAttributeNS(null, 'fill', sector.color);
			newSector.setAttributeNS(null, 'd', 'M' + sector.L + ',' + sector.L + ' L' + sector.L + ',0 A' + sector.L + ',' + sector.L + ' 0 ' + sector.arcSweep + ',1 ' + sector.X + ', ' + sector.Y + ' z');
			newSector.setAttributeNS(null, 'transform', 'rotate(' + sector.R + ', ' + sector.L + ', ' + sector.L + ')');

			newSVG.appendChild(newSector);
		})

		var midCircle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
		midCircle.setAttributeNS(null, 'cx', size * 0.5);
		midCircle.setAttributeNS(null, 'cy', size * 0.5);
		midCircle.setAttributeNS(null, 'r', size * 0.42);
		midCircle.setAttributeNS(null, 'fill', '#fff');

		newSVG.appendChild(midCircle);

		var txt = document.createElementNS("http://www.w3.org/2000/svg", 'text');
		if (percentage == 1)
			txt.setAttributeNS(null, 'x', size * (0.5 - 0.33));
		else
			txt.setAttributeNS(null, 'x', size * (0.5 - 0.20));
		txt.setAttributeNS(null, 'y', size * (0.5 + 0.10));
		txt.setAttributeNS(null, 'class', "sbs-piechart-text");
		txt.setAttributeNS(null, 'font-size', size * 0.30);
		txt.setAttributeNS(null, 'dy', "0.01");

		txt.textContent = Math.round(percentage * 100);

		var span = document.createElementNS("http://www.w3.org/2000/svg", "tspan");
		span.setAttributeNS(null, 'font-size', size * 0.20);
		span.setAttributeNS(null, 'dy', -1 * size * 0.07);
		span.textContent = "%";
		txt.appendChild(span);

		newSVG.appendChild(txt);
	};

	$b.save = function(fnc) {
		jbase.wait(true);
		jajax.apiAuthPut({
			url : jbase.urls.control_coopac + "/" + jcontrol.idCoopac + "/" + (jcontrol.percentage*100),
			data : jcontrol.controls,
			success : function(data) {
				jbase.wait(false);
				if (fnc)
					fnc();
			}
		});
	}

}(window.jcontrol));
