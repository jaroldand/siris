var app = new Vue({
    el: "#app",
    data: {
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
    	
    	departamentros_select : [],
    	canal_interrup_select : [],
    	canal_normal_select : [],
    	productos_select : [],
    	servicios_select : [],
    	procesos_select : [],
    	
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
						
						if(element.indCondNormal === '1'){
							self.productos_select.push({
		    		    		name: element.tipCanalDetalle,
		    		    		value: element.tbl_index,
		    		    		checked: element.indCondNormal === '1' ? true : false
		        		    });
						}
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
						
						if(element.indCondNormal === '1'){
							self.servicios_select.push({
		    		    		name: element.tipCanalDetalle,
		    		    		value: element.tbl_index,
		    		    		checked: element.indCondNormal === '1' ? true : false
		        		    });
						}
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
						
						if(element.indCondNormal === '1'){
							self.procesos_select.push({
		    		    		name: element.tipCanalDetalle,
		    		    		value: element.tbl_index,
		    		    		checked: element.indCondNormal === '1' ? true : false
		        		    });
						}
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
									
									self.canal_interrup_select.push({
				    		    		name: item.name,
				    		    		value: item.value,
				    		    		checked: true,
				    		    		attributes : _attr
				        		    });
									
									canal_normal.push({
				    		    		name: element.descCanalDetalle,
				    		    		value: element.tipCanalDetalle,
				    		    		checked: element.indCondNormal === '1' ? true : false,
				    		    		attributes : {
				    		    			idCanales: element.idCanales
				    		    		}
				        		    });
									
									if(element.indCondNormal === '1'){
										self.canal_normal_select.push({
					    		    		name: element.descCanalDetalle,
					    		    		value: element.tipCanalDetalle,
					    		    		checked: element.indCondNormal === '1' ? true : false,
					    		    		attributes : {
					    		    			idCanales: element.idCanales
					    		    		}
					        		    });
									}
									
								}
							});
						});
						
						//$('#canal_interrup').multiselect( 'loadOptions', canal_interrup);
						
		    			//$('#can_nor_interrup').multiselect( 'loadOptions', canal_normal);
						
						
						/*if(isNinguno !== "-1"){
							$('input:checkbox[value*="'+ isNinguno +'"]').trigger("click");
						}*/
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
										
										self.departamentros_select.push({
					    		    		name: item.name,
					    		    		value: element.tipCanalDetalle,
					    		    		checked: true,
					    		    		attributes : _attr
					        		    });
										
										
									}
								});
							}
						});
						//$('#regiones_interrup').multiselect( 'loadOptions', departamentros_select);
					}
				});
				
				//fin apiAuthGet - load data
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
        			var diff = fecha2.diff(fecha1, 'h'); // Diff in hours
        			
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
    	regresar: function(evt) {
    		flujoRegistro.setStep('module/tablero_registro','00');
    	},
    	descargar: function(_idDocumento) {
    		jbase.wait(true);
    		var urlinvokeDownload = jbase.getStringReplaced(jbase.urls.download_file_adic, [_idDocumento]);
			$("<iframe style='display: none' src='"+urlinvokeDownload+"'></iframe>").appendTo("body");
			jbase.wait(false);
    	},
    	descargar_constancia: function(evt) {
    		jbase.wait(true);
    		var urlinvokeDownload = jbase.getStringReplaced(jbase.urls.download_file_constancia, [jbase.prop.id_evento]);
			$("<iframe style='display: none' src='"+urlinvokeDownload+"'></iframe>").appendTo("body");
			jbase.wait(false);
     	},
     	enviar: function(evt) {
     		
     	},
     	enviar_informe: function(evt) {
     		
    		
     	},
     	prepare_data: function(evt) {
     		
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
            			
            			$( '#canal_interrup' ).next().find('> .ms-options > ul').find('input').each(function() {
                		    var $this = $(this);
                		    
                		    if($this.prop('value') !== thisOpt.val()){
                		    	$this.prop('disabled', false);
                		    }
            			});
            			
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
    	
    	$("#app").show();
    }

});
