var app = new Vue({
    el: "#app",
    data: {
    	update_pagination: false,
    	
    	reporte_active : true,//para el menu
    	historic_active : false,//para el menu
    	
    	//constants
    	tipo_productos : jbase.prop.tipo_productos,
    	tipo_servicios : jbase.prop.tipo_servicios,
    	tipo_procesos : jbase.prop.tipo_procesos,
    	
    	tipo_impacto_detail_ninguno : jbase.prop.tipo_impacto_detail_ninguno,
    	
    	
    	//para renderizar dependencias entre select's
    	tiempo_interrup_render : true,
    	
    	//combos
    	tipos_eventos : [],
    	impactos : [],
    	
    	
    	//grillas y multiselect
    	productos_list : [],
    	servicios_list : [],
    	procesos_list : [],
    	
    	//valores temporales para las grillas
    	producto_temp : null,
    	servicio_temp : null,
    	proceso_temp : null,
    	
    	desc_plan_temp : null,
    	area_resp_temp : null,
    	
    	is_evento_ciber : false,
    	is_upload_open : false,
    	is_otro_evento : false,
    	
    	impacto_load : {
    		financiero: {},
    		reputacional: {},
    		clientes_colaboradores: {},
    		regulatorio: {},
    		objetivos_estrategicos: {}
    	},
    	
        model: {
        	idEvento : jbase.prop.id_evento,
        	
        	productos : [],
        	servicios : [],
        	procesos : [],
        	
        	planAccion : [],
        	planAccionActivos : [],
        	
        	informe : {
        		
        	},
        	canales : [],
        	canalesActivos :[],
        	impacto : [],
        	correoInforme : {}
        }
    },
    
    beforeCreate : function() {
    	console.log("beforeCreate");
    	var self = this;
    	
    	//console.log("id evento!: " + jbase.prop.id_evento);
    	//console.log("tipo ent vigilada!: " + jbase.prop.tipo_ent_vig);
    	
    },
    
    created : function() {
    	console.log("created");
    	var self = this;
    	
    	//load tipos eventos
    	jajax.apiAuthGet({
			url : jbase.urls.tipos_eventos,
			success : function(data) {
				
				self.tipos_eventos = data;
			}
		});
    	
    	
    	var url_enviar = jbase.getStringReplaced(jbase.urls.load_reporte, [jbase.prop.id_evento]);
    	//load data
    	jajax.apiAuthGet({
			url : url_enviar,
			success : function(data) {
				
				self.model.informe = data.informe;
				
				self.model.correoInforme = data.correoInforme;
				
				self.model.informe.contactoNomb = jbase.isEmptyString( self.model.informe.contactoNomb ) ? jbase.prop.name_session_user : self.model.informe.contactoNomb;
				
				if(jbase.isEmptyString(self.model.informe.fileComentAdic)){
					self.is_upload_open = true;
				}
				
				$("#fecha_envio_interrup").val(self.model.informe.fecEnvioRepInicial);				
				
				$("#fecha_ini_interrup").val(self.model.informe.fecIniInterrupcion);
				var fecha1 = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
				
				if( fecha1.isValid() ){
	    			$("#fecha_impl_tmp").datetimepicker('destroy');
	    			$("#fecha_impl_tmp").datetimepicker({
	    				format : "L",
	    				locale : "es",
	    				minDate : fecha1,
	    				timeZone: 'Europe/London',
	    				sideBySide : true
	    			});
	    			
	    			$("#fecha_fin_interrup").datetimepicker('destroy');
	    			$("#fecha_fin_interrup").datetimepicker({
	    				format : "L",
	    				locale : "es",
	    				maxDate : 'now',
	    				minDate : fecha1,
	    				timeZone: 'Europe/London',
	    				sideBySide : true
	    			});
	    		}
				
				$("#fecha_fin_interrup").val(self.model.informe.fecFinInterrupcion);
				
				$("#hora_ini_interrup").val(self.model.informe.horIniInterrupcion);
				$("#hora_fin_interrup").val(self.model.informe.horFinInterrupcion);
				
				
				self.model.canales = data.canales;
				self.model.canalesActivos = data.canalesActivos;
				
				self.model.planAccion = data.planAccion;
				self.model.planAccionActivos = data.planAccionActivos;
				
				var k = 0;
				var i = 0;
				var j = 0;
				var productos_load = [];
				var servicios_load = [];
				var procesos_load = [];
				self.model.canales.forEach(function(element) {
					
					if( element.tipCanalGrupo === jbase.prop.tipo_productos ){
						element.tbl_index = "productos_"+k;
						k++;
						self.model.productos.push(element);
						//self.productos_render = true;
						
						productos_load.push({
	    		    		name: element.tipCanalDetalle,
	    		    		value: element.tbl_index,
	    		    		checked: element.indCondNormal === '1' ? true : false
	        		    });
					}
					
					if( element.tipCanalGrupo === jbase.prop.tipo_servicios ){
						element.tbl_index = "servicios_"+i;
						i++;
						self.model.servicios.push(element);
						//self.servicios_render = true;
						
						servicios_load.push({
	    		    		name: element.tipCanalDetalle,
	    		    		value: element.tbl_index,
	    		    		checked: element.indCondNormal === '1' ? true : false
	        		    });
					}
					
					if( element.tipCanalGrupo === jbase.prop.tipo_procesos ){
						element.tbl_index = "procesos_"+j;
						j++;
						self.model.procesos.push(element);
						//self.procesos_render = true;
						
						procesos_load.push({
	    		    		name: element.tipCanalDetalle,
	    		    		value: element.tbl_index,
	    		    		checked: element.indCondNormal === '1' ? true : false
	        		    });
					}
					
				});
				
				var z = 1;
				self.model.planAccion.forEach(function(element) {
					element.tbl_index = z;
					z++;
				});
				
				
				$('#productos').multiselect( 'loadOptions', productos_load);
				$('#servicios').multiselect( 'loadOptions', servicios_load);
				$('#procesos').multiselect( 'loadOptions', procesos_load);
				
				
				data.impacto.forEach(function(element) {
					
					if( element.tipoImpacto === jbase.prop.tipo_impacto_financiero ){
						self.impacto_load.financiero = element;
					}
					
					if( element.tipoImpacto === jbase.prop.tipo_impacto_reputacional ){
						self.impacto_load.reputacional = element;
					}

					if( element.tipoImpacto === jbase.prop.tipo_impacto_clientes_colaboradores ){
						self.impacto_load.clientes_colaboradores = element;
					}

					if( element.tipoImpacto === jbase.prop.tipo_impacto_regulatorio ){
						self.impacto_load.regulatorio = element;
					}

					if( element.tipoImpacto === jbase.prop.tipo_impacto_objetivos_estrategicos ){
						self.impacto_load.objetivos_estrategicos = element;
					}
					
				});
				
				
				//load canales by tipo_entidad
		    	var url_enviar = jbase.getStringReplaced(jbase.urls.load_canales, [jbase.prop.tipo_ent_vig]);
		    	jajax.apiAuthGet({
					url : url_enviar,
					success : function(data) {
						
						var canal_interrup = [];
						var canal_normal = [];
						
						data.forEach(function(element) {
							canal_interrup.push({
		    		    		name: element.value,
		    		    		value: element.key,
		    		    		checked: false,
		    		    		attributes : {
		    		    			disabled: false
		    		    		}
		        		    });

						});
						
						var isNinguno = "-1";
						console.log("lookup canales");
						console.log(self.model.canales);
						
						self.model.canales.forEach(function(element) {
							
							canal_interrup.forEach(function(item) {
								if( element.tipCanalGrupo === jbase.prop.tipo_canales_afectados && item.value === element.tipCanalDetalle && isNinguno === "-1"){
									
									var _attr = { idCanales: element.idCanales };
									item.attributes = _attr;
									item.checked = true;
									
									if(item.value === jbase.prop.ninguno_a || item.value === jbase.prop.ninguno_b || item.value === jbase.prop.ninguno_c){
										isNinguno = item.value;
										item.checked = false;
									}
									
									canal_normal.push({
				    		    		name: element.descCanalDetalle,
				    		    		value: element.tipCanalDetalle,
				    		    		checked: element.indCondNormal === '1' ? true : false,
				    		    		attributes : {
				    		    			idCanales: element.idCanales
				    		    		}
				        		    });
									
								}
							});
						});
						
						$('#canal_interrup').multiselect( 'loadOptions', canal_interrup);
						
		    			$('#can_nor_interrup').multiselect( 'loadOptions', canal_normal);
						
						
						if(isNinguno !== "-1"){
							$('input:checkbox[value*="'+ isNinguno +'"]').trigger("click");
						}
					}
				});
		    	
		    	
		    	//load departamentos
		    	jajax.apiAuthGet({
					url : jbase.urls.departamentos,
					success : function(data) {
						
						//self.departamentos = data;
						var departamentros = [];
						data.forEach(function(element) {
							departamentros.push({
		    		    		name: element.value,
		    		    		value: element.key,
		    		    		checked: false,
		    		    		attributes : {
		    		    			disabled: false
		    		    		}
		        		    });

						});
						
						
						self.model.canales.forEach(function(element) {
							if( element.tipCanalGrupo === jbase.prop.tipo_regiones_afectadas){
								departamentros.forEach(function(item) {
									if(item.value === element.tipCanalDetalle){
										
										var _attr = { idCanales: element.idCanales };
										item.attributes = _attr;
										item.checked = true;
									}
								});
							}
						});
						$('#regiones_interrup').multiselect( 'loadOptions', departamentros);
					}
				});
				
				//fin apiAuthGet - load data
		    	
		    	self.update_pagination = true;//para actualizar los tooltips en la grilla
			}
		});
    	
    	
    	//load impactos
    	jajax.apiAuthGet({
			url : jbase.urls.impactos,
			success : function(data) {
				
				self.impactos = data;
			}
		});
    	
    },
    
    watch: {
    	"model.informe.tipEvento": function() {
    		
    		this.is_evento_ciber = false;
    		this.is_otro_evento = false;
    		
    		if(this.model.informe.tipEvento === jbase.prop.tip_evento_003000){
    			this.is_evento_ciber = true;
    			this.model.informe.descTipEvento = null;
    		} else if(this.model.informe.tipEvento === jbase.prop.tip_evento_003009){
    			this.is_otro_evento = true;
    			this.model.informe.eventoSegBol = false;
    		} else{
    			this.model.informe.eventoSegBol = false;
    			this.model.informe.descTipEvento = null;
    		}
        },
        "model.informe.eventoSegBol": function() {
    		this.model.informe.isEventoSeg = this.model.informe.eventoSegBol === true ? "1" : "0";
    	},
    	"model.informe.eventoFinBol": function() {
    		this.model.informe.isEventoFin = this.model.informe.eventoFinBol === true ? "1" : "0";
    		
    		if(this.model.informe.eventoFinBol){
    			$("#fecha_fin_interrup").removeClass("send_required");
    			$("#hora_fin_interrup").removeClass("send_required");
    			
    			this.model.informe.fecFinInterrupcion = null;
    			this.model.informe.fecFinInterrupcionStr = null;
    			this.model.informe.horFinInterrupcion = null;
    			this.model.informe.totalInterrupcion = null;
    			
    			$("#fecha_fin_interrup").val("");
    			$("#hora_fin_interrup").val("");
    			$('#tiempo_interrup').val("");
    			
    			
    		}else{
    			$("#fecha_fin_interrup").addClass("send_required");
    			$("#hora_fin_interrup").addClass("send_required");
    		}
    	},
    	"tiempo_interrup_render": function() {
    		
    		if(this.tiempo_interrup_render){
    			
    			var fecha1 = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
    			var hora1 = moment( jbase.isEmptyString($('#hora_ini_interrup').val()) ? "90:90" : $('#hora_ini_interrup').val(), "HH:mm");
    			
        		var fecha2 = moment( $('#fecha_fin_interrup').val(), "DD/MM/YYYY");
        		var hora2 = moment( jbase.isEmptyString($('#hora_fin_interrup').val()) ? "90:90" : $('#hora_fin_interrup').val(), "HH:mm");
        		
        		
        		if( fecha1.isValid() && fecha2.isValid() && hora1.isValid() && hora2.isValid() ){
        			
        			var fechaIni = moment( $('#fecha_ini_interrup').val() + " " + $('#hora_ini_interrup').val(), "DD/MM/YYYY HH:mm");
        			var fechaFin = moment( $('#fecha_fin_interrup').val() + " " + $('#hora_fin_interrup').val(), "DD/MM/YYYY HH:mm");
        			
        			var diff = fechaFin.diff(fechaIni, 'h', true); // Diff in hours
        			
        			if(diff < 0){
        				jnoty.showMessage(jnoty.typeMessage.warning, "Verifique las fechas de interrupción!", 2000);
        			}
        		}
        		
        		
        		this.tiempo_interrup_render = false;
    		}
    	},
    	"impacto_load.objetivos_estrategicos.impactoDetail": function() {
    		if(this.impacto_load.objetivos_estrategicos.impactoDetail === jbase.prop.tipo_impacto_detail_ninguno){
    			this.impacto_load.objetivos_estrategicos.descripcion = null;
    		}
    	},
    	"impacto_load.regulatorio.impactoDetail": function() {
    		if(this.impacto_load.regulatorio.impactoDetail === jbase.prop.tipo_impacto_detail_ninguno){
    			this.impacto_load.regulatorio.descripcion = null;
    		}
    	},
    	"impacto_load.clientes_colaboradores.impactoDetail": function() {
    		if(this.impacto_load.clientes_colaboradores.impactoDetail === jbase.prop.tipo_impacto_detail_ninguno){
    			this.impacto_load.clientes_colaboradores.descripcion = null;
    		}
    	},
    	"impacto_load.reputacional.impactoDetail": function() {
    		if(this.impacto_load.reputacional.impactoDetail === jbase.prop.tipo_impacto_detail_ninguno){
    			this.impacto_load.reputacional.descripcion = null;
    		}
    	}
    },
    
    methods: {
    	load_data: function() {
    		
    	},
    	remove_afectacion: function(evt, name_table, index){
    		var self = this;
    		
    		//remove values multiselect combos
    		var checkeados = [];
    		var j = 0;
    		$('input:checkbox[value*="' + name_table + '"]').each(function() {
    		    var $this = $(this);
    		    
    		    if($this.prop('value') !== index){
    		    	checkeados.push({
    		    		name: $this.prop('title'),
    		    		value: name_table + "_" + j,
    		    		checked: $this.prop('checked')
        		    });
    		    	j++;
    		    }
    		});
    		$('#'+name_table).multiselect( 'loadOptions', checkeados);
    		
    		//remove values table
    		self.model[name_table] = self.model[name_table].filter(function(item) {
    		    return item.tbl_index !== index
    		});
    		
    		var k = 0;
    		Object.keys(self.model[name_table]).forEach(function(key) {
    			
    			self.model[name_table][key].tbl_index = name_table + "_" + k;
    			k++;
			});
    		
    	},
    	add_afectacion: function(evt, name_table, field, css_required, tipo){
    		var self = this;
    		
    		//validation empty
    		if (!jbase.validRegistro(css_required)) {
    			jnoty.showMessage(jnoty.typeMessage.warning, "Complete la información requerida!", 2000);
				return false;
			}
    		
    		//validation repeat
    		for (var i = 0; i < self.model[name_table].length; i++) {
    			
    			var _detalle = self.model[name_table][i].tipCanalDetalle;
    			
    			if( _detalle === self[field] )
				{
    				jnoty.showMessage(jnoty.typeMessage.warning, "La información ya se encuentra registrada!", 2000);
    				return false;
				}
    		}
    		
    		
    		//add data
    		var index_table = this.model[name_table].length;
    		self.model[name_table].push( {
    			tbl_index : name_table + "_" + index_table,
    			tipCanalDetalle : self[field],
    			tipCanalGrupo : tipo
    		});
    		
    		
    		//add values multiselect combos
    		var checkeados = [];
    		var j = 0;
    		$('input:checkbox[value*="' + name_table + '"]').each(function() {
    		    var $this = $(this);
    		    
		    	checkeados.push({
		    		name: $this.prop('title'),
		    		value: name_table + "_" + j,
		    		checked: $this.prop('checked')
    		    });
		    	j++;
    		});
    		
    		checkeados.push({
	    		name: self[field],
	    		value: name_table + "_" + index_table,
	    		checked: false
		    });
    		
    		$('#'+name_table).multiselect( 'loadOptions', checkeados);
    		
    		//clean data
    		self[field] = null;
    	},
    	add_plan_accion: function(evt, name_table, field1, field2, fecha, css_required){
    		var self = this;
    		
    		//validation empty
    		if (!jbase.validRegistro(css_required)) {
    			jnoty.showMessage(jnoty.typeMessage.warning, "Complete la información requerida!", 2000);
				return false;
			}
    		
    		//add data
    		var index_table = this.model[name_table].length + 1;
    		self.model[name_table].push( {
    			tbl_index: index_table,
    			descPlan: self[field1],
    			areaResp: self[field2],
    			fecImplementacion: $("#"+fecha).val(),
    			fecImplementacionStr: $("#"+fecha).val()
    		});
    		
    		
    		//clean data
    		self[field1] = null;
    		self[field2] = null;
    		
    		$("#"+fecha).val("");
    		$("#"+fecha).parent().removeClass( "is-dirty" );
    		
    		$(".tipsbs").tooltipster('destroy');
    		$(".tipsbs1").tooltipster('destroy');
    		
    		self.update_pagination = true;
    	},
    	remove_plan_accion: function(evt, name_table, index){
    		$(".tipsbs").tooltipster('destroy');
    		$(".tipsbs1").tooltipster('destroy');
    		//remove values table
    		var self = this;
    		
    		self.model[name_table] = self.model[name_table].filter(function(item) {
    		    return item.tbl_index !== index
    		});
    		
    		var k = 1;
    		Object.keys(self.model[name_table]).forEach(function(key) {
    			
    			self.model[name_table][key].tbl_index = k;
    			k++;
			});
    		
    		self.update_pagination = true;
    	},
    	calculaHoras: function() {
    		var self = this;
			self.tiempo_interrup_render = true;
    	},
    	cancelar: function(evt) {
    		
     		jnoty.confirm("No se grabrá la información ¿Desea cancelar?", function () { flujoRegistro.setStep('module/tablero_registro','00') } );
    	},
    	confirmar: function(evt) {
     		var self = this;
     		jnoty.confirm("¿Desea grabar la información?", function () {self.save_informe(); } );
     	},
     	enviar: function(evt) {
     		var self = this;
     		jnoty.confirm("¿Desea enviar la información a la SBS?", function () {self.enviar_informe(); } );
     	},
     	enviar_informe: function(evt) {
     		var self = this;
     		var is_no_valid = false;
     		$('#regiones_interrup').addClass("send_required");
     		$( '#canal_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
				var $this = $(this);
				
				if($this.prop('checked') && ( $this.prop('value') === jbase.prop.ninguno_a || $this.prop('value') === jbase.prop.ninguno_b || $this.prop('value') === jbase.prop.ninguno_c ) ){
					
					$('#regiones_interrup').removeClass("send_required");
				}
			});
     		
     		
     		/*if( !(self.model.productos && self.model.productos.length > 0) ){
     			
     			$("#product_interrup").val("");
     			jbase.validRegistro("required_afec_prod");
     			is_no_valid = true;
     		}
     		
     		if( !(self.model.servicios && self.model.servicios.length > 0) ){
     			
     			$("#servicios_interrup").val("");
     			jbase.validRegistro("required_afec_serv");
     			is_no_valid = true;
     		}
     		
     		if( !(self.model.procesos && self.model.procesos.length > 0) ){
     			
     			$("#procesos_interrup").val("");
     			jbase.validRegistro("required_afec_proc");
     			is_no_valid = true;
     		}*/
     		
     		
     		if( !(self.model.planAccion && self.model.planAccion.length > 0) ){
     			
     			$("#desc_plan_temp").val("");
     			$("#area_resp_temp").val("");
     			$("#fecha_impl_tmp").val("");
     			
     			jbase.validRegistro("required_plan_acc");
     			is_no_valid = true;
     		}
     		
     		
     		$('#desc_finan_interrup').removeClass("send_required");
     		$("#desc_finan_interrup").parent().removeClass("is-invalid");
     		if( !jbase.isEmptyString( self.impacto_load.financiero.impactoDetail ) ){
     			$('#desc_finan_interrup').addClass("send_required");
     		}
     		
     		$('#desc_repu_interrup').removeClass("send_required");
     		$("#desc_repu_interrup").parent().removeClass("is-invalid");
     		if( self.impacto_load.reputacional.impactoDetail !== jbase.prop.tipo_impacto_detail_ninguno ){
     			$('#desc_repu_interrup').addClass("send_required");
     		}
     		
     		$('#desc_cli_interrup').removeClass("send_required");
     		$("#desc_cli_interrup").parent().removeClass("is-invalid");
     		if( self.impacto_load.clientes_colaboradores.impactoDetail !== jbase.prop.tipo_impacto_detail_ninguno ){
     			$('#desc_cli_interrup').addClass("send_required");
     		}
     		
     		$('#desc_reg_interrup').removeClass("send_required");
     		$("#desc_reg_interrup").parent().removeClass("is-invalid");
     		if( self.impacto_load.regulatorio.impactoDetail !== jbase.prop.tipo_impacto_detail_ninguno ){
     			$('#desc_reg_interrup').addClass("send_required");
     		}
     		
     		$('#desc_obj_interrup').removeClass("send_required");
     		$("#desc_obj_interrup").parent().removeClass("is-invalid");
     		if( self.impacto_load.objetivos_estrategicos.impactoDetail !== jbase.prop.tipo_impacto_detail_ninguno ){
     			$('#desc_obj_interrup').addClass("send_required");
     		}
     		
     		
     		if (!jbase.validRegistro("send_required")) {
     			is_no_valid = true;
			}
     		
     		
     		if(is_no_valid){
     			jnoty.showStickyMessage(jnoty.typeMessage.warning, "Complete la información requerida!", false);
				return false;
     		}
     		
     		
     		var fecha1 = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
			var hora1 = moment( jbase.isEmptyString($('#hora_ini_interrup').val()) ? "90:90" : $('#hora_ini_interrup').val(), "HH:mm");
			
    		var fecha2 = moment( $('#fecha_fin_interrup').val(), "DD/MM/YYYY");
    		var hora2 = moment( jbase.isEmptyString($('#hora_fin_interrup').val()) ? "90:90" : $('#hora_fin_interrup').val(), "HH:mm");
    		
    		
    		if( fecha1.isValid() && fecha2.isValid() && hora1.isValid() && hora2.isValid() ){
    			var fechaIni = moment( $('#fecha_ini_interrup').val() + " " + $('#hora_ini_interrup').val(), "DD/MM/YYYY HH:mm");
    			var fechaFin = moment( $('#fecha_fin_interrup').val() + " " + $('#hora_fin_interrup').val(), "DD/MM/YYYY HH:mm");
    			
    			var diff = fechaFin.diff(fechaIni, 'h', true); // Diff in hours
    			
    			$("#fecha_ini_interrup").parent().removeClass("is-invalid");
				$("#fecha_fin_interrup").parent().removeClass("is-invalid");
				$("#hora_ini_interrup").parent().removeClass("is-invalid");
				$("#hora_fin_interrup").parent().removeClass("is-invalid");
    			
    			if(diff < 0){
    				$("#fecha_ini_interrup").parent().addClass("is-invalid");
    				$("#fecha_fin_interrup").parent().addClass("is-invalid");
    				$("#hora_ini_interrup").parent().addClass("is-invalid");
    				$("#hora_fin_interrup").parent().addClass("is-invalid");
    				
    				jnoty.showMessage(jnoty.typeMessage.warning, "Verifique las fechas de interrupción ingresadas!", 2000);
    				return false;
    			}
    		}
    		
    		var fecha_1 = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
    		var fecha_2 = moment( $('#fecha_envio_interrup').val(), "DD/MM/YYYY");
    		
    		if( fecha_1.isValid() && fecha_2.isValid() ){
    			var fecha1Ini = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
    			var fecha2Fin = moment( $('#fecha_envio_interrup').val(), "DD/MM/YYYY");
    			
    			var diff = fecha2Fin.diff(fecha1Ini, 'h', true); // Diff in hours
    			
    			$("#fecha_ini_interrup").parent().removeClass("is-invalid");
				$("#fecha_envio_interrup").parent().removeClass("is-invalid");
    			
    			if(diff < 0){
    				$("#fecha_ini_interrup").parent().addClass("is-invalid");
    				$("#fecha_envio_interrup").parent().addClass("is-invalid");
    				
    				jnoty.showMessage(jnoty.typeMessage.warning, "La fecha de inicio de interrupción no puede ser mayor a la fecha de envío de interrupción!", 2000);
    				return false;
    			}
    		}
    		
    		//validamos la fecha de impl de plan de acción debería ser mayor a la fecha de inicio de interrup.
    		for (var i = 0; i < self.model.planAccion.length; i++) {
    			
    			var fechaIni = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
    			var fechaFin = moment( self.model.planAccion[i].fecImplementacionStr, "DD/MM/YYYY");
    			
    			var diff = fechaFin.diff(fechaIni, 'h', true); // Diff in hours
    			
    			if(diff < 0){
    				$("#fecha_impl_tmp").parent().addClass("is-invalid");
    				
    				jnoty.showMessage(jnoty.typeMessage.warning, "La fecha de implementación de planes de acción debería ser siempre mayores que la fecha de inicio de interrupción!", 2000);
    				return false;
    			}
    		}
    		
    		$("#mail_interrup").parent().removeClass("is-invalid");
    		if( !jbase.isValidEmail(self.model.correoInforme.desCorreo) ){
    			$("#mail_interrup").parent().addClass("is-invalid");
    			jnoty.showMessage(jnoty.typeMessage.warning, "El formato de correo no es correcto!", 2000);
				return false;
    		}
     		
    		self.prepare_data();
    		
    		jbase.wait(true);
    		jajax.apiAuthPost({
    			url : jbase.urls.enviar_reporte,
    			data : self.model,
    			success : function(data) {
    				//pasar a la pagina de lectura
    				jbase.wait(false);
    				console.log(data.informe.fecEnvio);
    				jnoty.showMessage(jnoty.typeMessage.success, "El reporte fue enviado con éxito a la fecha: <br/> "+ data.informe.fecEnvio, 3000);
    				flujoRegistro.setStep('module/informe_constancia/'+data.idEvento,'00');//refresh para actualizar los id's
    			}
    		});
    		
     	},
     	prepare_data: function(evt) {
     		var self = this;
     		
     		self.model.informe.fecEnvioRepInicialStr = $("#fecha_envio_interrup").val();
     		
     		self.model.informe.fecIniInterrupcionStr = $("#fecha_ini_interrup").val();
     		self.model.informe.fecFinInterrupcionStr = $("#fecha_fin_interrup").val();
     		
     		
     		self.model.informe.horIniInterrupcion = $("#hora_ini_interrup").val();
     		self.model.informe.horFinInterrupcion = $("#hora_fin_interrup").val();
     		self.model.informe.totalInterrupcion = $('#tiempo_interrup').val();
     		
     		self.model.informe.tipEventoDesc = $("#tipo_evento option:selected").text().trim();
     		self.model.informe.eventoSegDesc = self.model.informe.eventoSegBol === true ? "SI" : "NO";
     		self.model.informe.eventoFinDesc = self.model.informe.eventoFinBol === true ? "SI" : "NO";
     		
     		self.model.canales = self.model.productos.concat(self.model.servicios).concat(self.model.procesos);
     		
     		
     		$( '#canal_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
				var $this = $(this);
				
				if($this.prop('checked')){
					self.model.canales.push({
						idCanales : $this.attr('idCanales'),
						tipCanalGrupo : jbase.prop.tipo_canales_afectados,
            			tipCanalDetalle : $this.prop('value'),
            			indCondNormal : ( $( '#can_nor_interrup' ).next().find('> .ms-options > ul').find("input:checkbox[value*='"+$this.prop('value')+"']").prop('checked') ? '1' : '0'),
            			descCanalDetalleStr : $this.prop('title')
					});
				}
				
			});
     		
     		$( '#regiones_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
				var $this = $(this);
				
				if($this.prop('checked')){
					self.model.canales.push({
						idCanales : $this.attr('idCanales'),
						tipCanalGrupo : jbase.prop.tipo_regiones_afectadas,
            			tipCanalDetalle : $this.prop('value'),
            			descCanalDetalleStr : $this.prop('title')
					});
				}
				
			});
     		
     		self.model.productos.forEach(function(item) {
     			item.indCondNormal = ( $( '#productos' ).next().find('> .ms-options > ul').find("input:checkbox[value*='"+ item.tbl_index +"']").prop('checked') ? '1' : '0');
     			item.descCanalDetalleStr = item.tipCanalDetalle;
     		});
     		
     		self.model.servicios.forEach(function(item) {
     			item.indCondNormal = ( $( '#servicios' ).next().find('> .ms-options > ul').find("input:checkbox[value*='"+ item.tbl_index +"']").prop('checked') ? '1' : '0');
     			item.descCanalDetalleStr = item.tipCanalDetalle;
     		});
     		
     		self.model.procesos.forEach(function(item) {
     			item.indCondNormal = ( $( '#procesos' ).next().find('> .ms-options > ul').find("input:checkbox[value*='"+ item.tbl_index +"']").prop('checked') ? '1' : '0');
     			item.descCanalDetalleStr = item.tipCanalDetalle;
     		});
     		
     		self.impacto_load.financiero.labelReport = "Financiero";
     		self.impacto_load.reputacional.impactoDetailStr = self.impacto_load.financiero.impactoDetail;
     		
     		self.impacto_load.reputacional.labelReport = "Reputacional";
     		self.impacto_load.reputacional.impactoDetailStr = $("#imp_repu_interrup option:selected").text().trim();
     		
     		self.impacto_load.clientes_colaboradores.labelReport = "Clientes/colaboradores";
     		self.impacto_load.clientes_colaboradores.impactoDetailStr = $("#imp_cli_interrup option:selected").text().trim();
     		
     		self.impacto_load.regulatorio.labelReport = "Regulatorio";
     		self.impacto_load.regulatorio.impactoDetailStr = $("#imp_reg_interrup option:selected").text().trim();
     		
     		self.impacto_load.objetivos_estrategicos.labelReport = "Objetivos estratégico";
     		self.impacto_load.objetivos_estrategicos.impactoDetailStr = $("#imp_obj_interrup option:selected").text().trim();
     		
     		self.model.impacto = [
	     			self.impacto_load.financiero,
	     			self.impacto_load.reputacional,
	     			self.impacto_load.clientes_colaboradores,
	     			self.impacto_load.regulatorio,
	     			self.impacto_load.objetivos_estrategicos
	     		]; 
     	},
     	save_informe: function(evt) {
     		var self = this;
     		
     		self.prepare_data();
     		
     		jbase.wait(true);
    		jajax.apiAuthPost({
    			url : jbase.urls.save_reporte,
    			data : self.model,
    			success : function(data) {
    				
    				//self.model.informe = data.informe;
    				
    				jbase.wait(false);
    				flujoRegistro.setStep('module/informe_reporte/'+data.idEvento,'00');//refresh para actualizar los id's
    				
    				jnoty.showMessage(jnoty.typeMessage.success, "Se grabó correctamente", 2000);
    				
    			}
    		});
     	},
     	deleteUpload: function() {
    		var self = this;
    		
    		jnoty.confirm("Se eliminará el archivo ¿Desea continuar?", function () {
    			
    			jbase.wait(true);
    			var url_enviar = jbase.getStringReplaced(jbase.urls.delete_file_adic, [jbase.prop.id_evento, self.model.informe.fileComentAdic ]);
		    	jajax.apiAuthGet({
					url : url_enviar,
					success : function(data) {
						jbase.wait(false);
        				
        				self.model.informe.fileComentAdic = null;
        				$("#mdr_upload").val(null);
                		self.is_upload_open = true;
        				jnoty.showMessage(jnoty.typeMessage.success, "Se eliminó correctamente", 2000);
					}
		    	});
        		
    		} );
    		
    	},
    	descargar: function(_idDocumento) {
    		jbase.wait(true);
    		var urlinvokeDownload = jbase.getStringReplaced(jbase.urls.download_file_adic, [_idDocumento]);
			$("<iframe style='display: none' src='"+urlinvokeDownload+"'></iframe>").appendTo("body");
			jbase.wait(false);
    	},
    	uploadFile: function(file_upload) {
    		var self = this;
    		
    		$("#mdr_upload").parent().removeClass( "is-invalid" );
    		$("#mdr_upload").parent().addClass( "is-dirty" );
    		
    		
    		var form = new FormData();
    		
    		var archtipo = jbase.getCodExtension(file_upload.name)
    		
    		form.append("idFile", jbase.isEmptyString(self.model.informe.fileComentAdic) === true ? -1 : self.model.informe.fileComentAdic);
    		form.append("desDocumento", file_upload.name.substring(0, file_upload.name.lastIndexOf(".")));
			form.append("tipDocumento", archtipo.type);
			form.append("desExtension", archtipo.name);
			form.append("numTamanio", file_upload.size);
			form.append("idEvento", jbase.prop.id_evento);
			form.append("file", file_upload);
			form.append("idInforme", self.model.informe.idInforme);
    		
			jbase.wait(true);
    		jajax.apiAuthUploadPost({
    			url : jbase.urls.subir_archivo,
				data : form,
				success : function(data) {
					jbase.wait(false);
					
					$("#mdr_upload").val(file_upload.name);
					
					self.model.informe.fileComentAdic = data;
					
					jnoty.showMessage(jnoty.typeMessage.success, "Se adjuntó correctamente", 2000);
				},
				error : function(xhr, textStatus, error) {
					jnoty.information("No se pudo procesar el archivo!");
					jbase.wait(false);
					return false;
				}
    		});
    	},
     	handleFileUpload: function() {
    		var self = this;
    		
    		var file_upload = this.$refs.mdr_fileDoc.files[0];
    		
    		if (file_upload.size > 5242880) {
				jnoty.warning("El archivo pesa más de 5MB");
				return false;
			}
    		if (jbase.getCodExtension(file_upload.name) === null) {
				jnoty.warning("El formato del archivo no es válido.");
				return false;
			}
    		
    		jnoty.confirm("Se adjuntará el archivo al reporte ¿Desea continuar?", function () {self.uploadFile(file_upload); } );
    		
    	},
    	calcularTotalHoras: function(id_component) {
    		var self = this;
    		
    		var fecha1 = moment( $('#fecha_ini_interrup').val(), "DD/MM/YYYY");
    		var hora1 = moment( jbase.isEmptyString($('#hora_ini_interrup').val()) || $('#hora_ini_interrup').val().length != 5 ? "90:90" : $('#hora_ini_interrup').val(), "HH:mm");
    		
    		if( fecha1.isValid() ){
    			$("#fecha_impl_tmp").datetimepicker('destroy');
    			$("#fecha_impl_tmp").datetimepicker({
    				format : "L",
    				locale : "es",
    				minDate : fecha1,
    				timeZone: 'Europe/London',
    				sideBySide : true
    			});
    			
    			$("#fecha_fin_interrup").datetimepicker('destroy');
    			$("#fecha_fin_interrup").datetimepicker({
    				format : "L",
    				locale : "es",
    				maxDate : 'now',
    				minDate : fecha1,
    				timeZone: 'Europe/London',
    				sideBySide : true
    			});
    		}
    		
    		if(self.model.informe.eventoFinBol != true){
    			console.log("calcularHoras");
    			
        		var fecha2 = moment( $('#fecha_fin_interrup').val(), "DD/MM/YYYY");
        		var hora2 = moment( jbase.isEmptyString($('#hora_fin_interrup').val()) || $('#hora_fin_interrup').val().length != 5 ? "90:90" : $('#hora_fin_interrup').val(), "HH:mm");
        		
        		$('#tiempo_interrup').val("");
        		this.model.informe.totalInterrupcion = null;
        		
        		if( fecha1.isValid() && fecha2.isValid() && hora1.isValid() && hora2.isValid() ){
        			
        			var fechaIni = moment( $('#fecha_ini_interrup').val() + " " + $('#hora_ini_interrup').val(), "DD/MM/YYYY HH:mm");
        			var fechaFin = moment( $('#fecha_fin_interrup').val() + " " + $('#hora_fin_interrup').val(), "DD/MM/YYYY HH:mm");
        			
        			var fechaIniValid = moment(new Date(), 'DD/MM/YYYY HH:mm').diff(fechaIni, 'h', true) > 0 ? true : false;//verificamos que la fecha hora no supere a la actual
        			var fechaFinValid = moment(new Date(), 'DD/MM/YYYY HH:mm').diff(fechaFin, 'h', true) > 0 ? true : false;//verificamos que la fecha hora no supere a la actual
        			
        			if( !fechaIniValid ){
        				$('#hora_ini_interrup').val("");
        			}
        			if( !fechaFinValid ){
        				$('#hora_fin_interrup').val("");
        			}
        			
        			if( fechaIniValid && fechaFinValid ){
        				var diff = fechaFin.diff(fechaIni, 'h', true); // Diff in hours
            			
            			if(diff < 0){
            				
            				$('#tiempo_interrup').val("");
                    		this.model.informe.totalInterrupcion = null;
            				
                    		jnoty.showMessage(jnoty.typeMessage.warning, "Verifique las fechas ingresadas!", 2000);
                    		$('#'+id_component).val("");
            			}else{
            				$('#tiempo_interrup').val(jbase.redondear(diff, 2));
                    		this.model.informe.totalInterrupcion = jbase.redondear(diff, 2);
            			}
        			}
        			else{
        				jnoty.showMessage(jnoty.typeMessage.warning, "La fecha y hora no puede ser mayor que la fecha y hora actual!", 2000);
        			}
        		}
    			
    		}
    	}
    },
    
    updated: function() {
    	var self = this;
    	
    	
		//mask for hours
		var momentFormat = 'HH:mm';
		var maskOptions =
		{
			mask: Date,
			pattern: momentFormat,
			lazy: true,
			patternToClean: "^([0-2])([0-9]):([0-5])([0-9])$",
			min: new Date(1900, 0, 1),
			max: new Date(3000, 0, 1),
			
			format: function (date) {
				return moment(date).format(momentFormat);
			},
			parse: function (str) {
				return moment(str, momentFormat);
			},
			
			blocks: {
				
				YYYY: {
				  mask: IMask.MaskedRange,
				  from: 1970,
				  to: 2030
				},
				MM: {
				  mask: IMask.MaskedRange,
				  from: 1,
				  to: 12
				},
				DD: {
				  mask: IMask.MaskedRange,
				  from: 1,
				  to: 31
				},
				
				HH: {
				  mask: IMask.MaskedRange,
				  from: 0,
				  to: 23
				},
				mm: {
				  mask: IMask.MaskedRange,
				  from: 0,
				  to: 59
				}
			}
			
		};
		var hourini = IMask( document.getElementById('hora_ini_interrup'), maskOptions );
		hourini.updateValue();
		
		var hourfin = IMask( document.getElementById('hora_fin_interrup'), maskOptions );
		hourfin.updateValue();
    	
    	
    	if(self.tiempo_interrup_render){
    		self.tiempo_interrup_render = false;
    	}
    	
    	if(this.update_pagination){
    		
    		this.model.planAccion.forEach(
				function(e){
        			$("#"+ e.tbl_index + "tipsbs").tooltipster({
        			       maxWidth: 500,
        			       side: 'bottom',
        			       content : e.descPlan,
        			       contentAsHTML: 'true'
        			    });
        			$("#"+ e.tbl_index + "tipsbs1").tooltipster({
     			       maxWidth: 500,
     			       side: 'bottom',
     			       content : e.areaResp,
     			       contentAsHTML: 'true'
     			    });
        		}
	        );
    		
    		this.update_pagination = false;
    		
    	}
    },
    
    mounted: function() {
    	
    	var self = this;
    	
    	
    	$('.multiselect_sbs').multiselect({
            placeholder: '',
            search: true,
            searchOptions: {
                'default': 'Búsqueda'
            },
            selectAll: false,
            onOptionClick: function( element, option ){
                
            	var thisOpt = $(option);
            	/*INICIO DE CANALES*/
            	if("canal_interrup" === $(element).prop('id') && thisOpt.prop('checked')){//evento al hacer check

            		
            		if(thisOpt.val() !== jbase.prop.ninguno_a && thisOpt.val() !== jbase.prop.ninguno_b && thisOpt.val() !== jbase.prop.ninguno_c){//marca distinto a ninguno
            			
            			var checkeados = [];
            			$( '#can_nor_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
            				var $this = $(this);
            				
            				console.log(thisOpt.attr('idCanales'));
            				
            				checkeados.push({
            		    		name: $this.prop('title'),
            		    		value: $this.prop('value'),
            		    		checked: $this.prop('checked'),
            		    		attributes : {
		    		    			idCanales: $this.attr('idCanales')
		    		    		}
                		    });
            			});
            			
            			checkeados.push({
        		    		name: thisOpt.prop('title'),
        		    		value: thisOpt.val(),
        		    		checked: false,
        		    		attributes : {
	    		    			idCanales: thisOpt.attr('idCanales')
	    		    		}
            		    });
            			$('#can_nor_interrup').multiselect( 'loadOptions', checkeados);
            			
            		} else {//marca la opcion Ninguno
            			
            			var checkeados = [];
            			$( '#canal_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
                		    var $this = $(this);
                		    
                		    var ischeck = false;
                		    var isdisable = true;
                		    if($this.prop('value') === thisOpt.val()){
                		    	isdisable = false;
                		    	ischeck = true;
                		    }
                		    
                		    checkeados.push({
            		    		name: $this.prop('title'),
            		    		value: $this.prop('value'),
            		    		checked: ischeck,
            		    		attributes : {
            		    			disabled: isdisable
            		    		}
                		    });
                		    
            			});
            			
            			$('#canal_interrup').multiselect( 'loadOptions', checkeados);
            			$('#regiones_interrup').multiselect('reset');
            			$('#can_nor_interrup').multiselect( 'loadOptions', []);
            			
            			$('#regiones_interrup').multiselect('disable');
            			$('#can_nor_interrup').multiselect('disable');
            		}
            		
            	} else if("canal_interrup" === $(element).prop('id') && !thisOpt.prop('checked')){//evento al hacer no check
            		
            		if(thisOpt.val() !== jbase.prop.ninguno_a && thisOpt.val() !== jbase.prop.ninguno_b && thisOpt.val() !== jbase.prop.ninguno_c){//marca distinto a ninguno
            		
	            		//remove values multiselect combos
	            		var checkeados = [];
	            		$( '#can_nor_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
	            		    var $this = $(this);
	            		    
	            		    if($this.prop('value') !== thisOpt.val()){
	            		    	checkeados.push({
	            		    		name: $this.prop('title'),
	            		    		value: $this.prop('value'),
	            		    		checked: $this.prop('checked')
	                		    });
	            		    }
	            		});
	            		$('#can_nor_interrup').multiselect( 'loadOptions', checkeados);
            		
            		} else {//marca la opcion Ninguno
            			
            			var checkeados = [];
            			$( '#canal_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
                		    var $this = $(this);
                		    
                		    if($this.prop('value') !== thisOpt.val()){
                		    	$this.prop('disabled', false);
                		    }
                		    
                		    
                		    checkeados.push({
            		    		name: $this.prop('title'),
            		    		value: $this.prop('value'),
            		    		checked: false,
            		    		attributes : {
            		    			disabled: false
            		    		}
                		    });
                		    
            			});
            			
            			$('#canal_interrup').multiselect( 'loadOptions', checkeados);
            			
            			$('#regiones_interrup').multiselect('disable', false);
            			$('#can_nor_interrup').multiselect('disable', false);
            			
            		}
            		
            	}
            	/*FIN DE CANALES*/
            }
        });
    	
    	jbase.decimal(1);//decimal 1 place
    	jbase.positive_integer();//positive-integer
    	
    	jbase.formatDate(true);
    	jbase.customFormatDate(true, '.sbs-date-nomaxdate');
    	
    	$("#app").show();
    }

});
