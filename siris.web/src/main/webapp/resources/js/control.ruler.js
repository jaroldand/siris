window.jcontrol = window.jcontrol || {};

(function($b) {

	$b.rulerIn = function(that, codControl, llenado, final) {

		if (codControl.indexOf("P02") >=0 ) { // Tipo de Teléfono
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == codControl;// Se obtiene al control
			});

			if (!obj || obj.length <= 0)
				return;

			if (that.val() === "-" || that.val() === "'")//Si es guion no se considera llenado
			{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

		}

		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P01_TIPO_TELEF: Tipo de Teléfono
		// ------------------------------------------------------------------------------------------>

		if (codControl === "P01_TIPO_TELEF") { // Tipo de Teléfono

			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P01_COD_CIUDAD";// Código de Ciudad
			});

			if (!obj || obj.length <= 0)
				return;

			if (that.val() === '005001')// TELEFONO FIJO
				obj[0].indActivo = 1;
			else
				obj[0].indActivo = 0;

			var val = $("[control='P01_COD_CIUDAD']").val();
			if (val != undefined && val != null && val != "")
				obj[0].indLlenado = 1;
			else
				obj[0].indLlenado = 0;
			
			if(obj[0].indActivo == 1 && obj[0].indLlenado == 0){
				final[0].indLlenado = 0;
			}

		}

		// ------------------------------------------------------------------------------------------>
		// ------------------------------ Valida Total Consejos
		// ------------------------------------------------------------------------------------------>
		if (codControl.indexOf("P03_TOTAL_") >=0) { // Consejo de XXXX
			
			var tipoConsejo = codControl.substring("P03_TOTAL_".length, codControl.length);
			
			var obj = jcontrol.controls.filter(function(data) {
				// El número de personas no coincide con la cantidad ingresada.
				return data.codControl == "P03_FILA_TOTAL_"+tipoConsejo;
			});

			if (!obj || obj.length <= 0)
				return;

			var lst;
			if("ADM" === tipoConsejo) lst = app.model.consejo_adm;
			if("EDU" === tipoConsejo) lst = app.model.consejo_edu;
			if("ELEC" === tipoConsejo) lst = app.model.consejo_elec;
			if("VIG" === tipoConsejo) lst = app.model.consejo_vig;

			obj[0].indActivo = 1;
			if (parseInt(that.val()) === lst.length){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ Valida Declaracion jurada de impedimentos antes 2019
		// ------------------------------------------------------------------------------------------>
		if(codControl === "P04_DEC_JUR_IMP_ANT_2019") {
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP_ANT_2019";
			});

			if (!obj || obj.length <= 0)
				return;
			
			
			obj[0].indActivo = 0;
			obj[0].indLlenado = 0;
			if( that.is(":checked") ){
				obj[0].indActivo = 1;
				obj[0].indLlenado = 1;
			}else { final[0].indLlenado = 0; }
			
			
			
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP_LISTA";
			});

			if (!obj || obj.length <= 0)
				return;
			
			obj[0].indActivo = 0;
			if( that.is(":checked") ){
				obj[0].indActivo = 1;
			}
			
			var val = app.model.table_directivos_imp.length;
			if (val > 0){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}
			
			
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP";
			});
			
			obj[0].indActivo = 1;
			obj[0].indLlenado = 0;
			if( that.is(":checked") ){
				obj[0].indActivo = 0;
			}
			
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ Valida Declaracion jurada de impedimentos sin listado
		// ------------------------------------------------------------------------------------------>
		if(codControl === "P04_DEC_JUR_IMP") {
			
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP";
			});

			if (!obj || obj.length <= 0)
				return;
			
			
			obj[0].indActivo = 0;
			obj[0].indLlenado = 0;
			if( that.is(":checked") ){
				obj[0].indActivo = 1;
				obj[0].indLlenado = 1;
			}else { final[0].indLlenado = 0; }
			
			
			
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP_LISTA";
			});

			if (!obj || obj.length <= 0)
				return;
			
			//obj[0].indActivo = 1;
			//if( that.is(":checked") ){
				obj[0].indActivo = 0;
			//}
			
			
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP_ANT_2019";
			});

			if (!obj || obj.length <= 0)
				return;
			
			obj[0].indActivo = 1;
			obj[0].indLlenado = 0;
			if( that.is(":checked") ){
				obj[0].indActivo = 0;
			}
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ Valida fecha paso 2 VS. paso 5
		// ------------------------------------------------------------------------------------------>
		if(codControl === "P02_FEC_INFOR") {
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P05_FEC_INF_MAX_2_MESES";
			});

			if (!obj || obj.length <= 0)
				return;
			
			
			
			var fecha_infor = that.val();
			if (!fecha_infor){
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}
		}

	};
	
	$b.rulerLoad = function(name, data) {
		if(name === "enviar"){
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P05_FEC_INF_MAX_2_MESES";
			});
			
			if (!obj || obj.length <= 0)
				return;
			
			
			obj[0].indLlenado = 0;
			if(data === 1){
				obj[0].indLlenado = 1;
			}
			
		}
		
		jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className, jcontrol.size);
	};

	$b.rulerOn = function(codControl) {
		
		var final = jcontrol.controls.filter(function(data) {
			return data.codControl == "P05_IS_FINAL";
		});

		if (!final || final.length <= 0)
			return;
		

		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P03_FILA_GER: Gerente
		// ------------------------------------------------------------------------------------------>

		if (codControl === "gerente") {
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P03_FILA_GER";
			});

			if (!obj || obj.length <= 0)
				return;

			var lst = app.model.gerente;
			if (lst.length > 0){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			//return;
		}

		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P03_FILA_TOTAL_ADM: Consejo de Administración
		// ------------------------------------------------------------------------------------------>
		if (codControl === "consejo_adm") {
			var obj = jcontrol.controls.filter(function(data) {
				// valida el número de personas no coincide con la cantidad ingresada.
				return data.codControl == "P03_FILA_TOTAL_ADM";
			});

			if (!obj || obj.length <= 0)
				return;

			var lst = app.model.consejo_adm;
			
			obj[0].indActivo = 1;
			if (parseInt(app.model.total_consejo_adm) === lst.length && lst.length > 0 ){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			//return;
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P03_FILA_TOTAL_EDU: Comite de educacion
		// ------------------------------------------------------------------------------------------>
		if (codControl === "consejo_edu") {
			var obj = jcontrol.controls.filter(function(data) {
				// El número de personas no coincide con la cantidad ingresada.
				return data.codControl == "P03_FILA_TOTAL_EDU";
			});

			if (!obj || obj.length <= 0)
				return;

			var lst = app.model.consejo_edu;

			obj[0].indActivo = 1;
			if (parseInt(app.model.total_consejo_edu) === lst.length && lst.length > 0 ){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			//return;
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P03_FILA_TOTAL_ELEC: Consejo electoral
		// ------------------------------------------------------------------------------------------>
		if (codControl === "consejo_elec") {
			var obj = jcontrol.controls.filter(function(data) {
				// El número de personas no coincide con la cantidad ingresada.
				return data.codControl == "P03_FILA_TOTAL_ELEC";
			});

			if (!obj || obj.length <= 0)
				return;

			var lst = app.model.consejo_elec;

			obj[0].indActivo = 1;
			if (parseInt(app.model.total_consejo_elec) === lst.length && lst.length > 0 ){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			//return;
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P03_FILA_TOTAL_VIG: Consejo de vigilancia
		// ------------------------------------------------------------------------------------------>
		if (codControl === "consejo_vig") {
			var obj = jcontrol.controls.filter(function(data) {
				// El número de personas no coincide con la cantidad ingresada.
				return data.codControl == "P03_FILA_TOTAL_VIG";
			});

			if (!obj || obj.length <= 0)
				return;

			var lst = app.model.consejo_vig;

			obj[0].indActivo = 1;
			if (parseInt(app.model.total_consejo_vig) === lst.length && lst.length > 0 ){
				obj[0].indLlenado = 1;
			}
			else{
				obj[0].indLlenado = 0;
				final[0].indLlenado = 0;
			}

			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			//return;
		}
		
		
		// ------------------------------------------------------------------------------------------>
		// ------------------------------ P04_DEC_JUR_IMP_LISTA: Lista declar jurada con impedimentos
		// ------------------------------------------------------------------------------------------>
		if (codControl === "table_directivos_imp") {
			var obj = jcontrol.controls.filter(function(data) {
				return data.codControl == "P04_DEC_JUR_IMP_LISTA";
			});
			
			if (!obj || obj.length <= 0)
				return;
			
			var lst = app.model.table_directivos_imp;
			obj[0].indLlenado = 0;
			if(lst.length > 0){
				obj[0].indLlenado = 1;
			}else { final[0].indLlenado = 0; }
			
			jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className,
					jcontrol.size);
			
			//return;
		}
		
		//VALIDAR los pasos del 1 al 4
		if( $("#P01").hasClass("flow-step-success") &&
			$("#P02").hasClass("flow-step-success") &&
			$("#P03").hasClass("flow-step-success") &&
			$("#P04").hasClass("flow-step-success")
		  ){
			final[0].indLlenado = 1;
		}
		
		// actualizar
		jcontrol.updateDraw(jcontrol.idCoopac, jcontrol.className, jcontrol.size);
	};

}(window.jcontrol));
