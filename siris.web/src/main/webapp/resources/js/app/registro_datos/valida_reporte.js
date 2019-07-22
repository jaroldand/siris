
var app = new Vue({
    el: "#app",
    data: {
    	reporte_active : true,
    	historic_active : false,
        model: {
        	checkA : {},
        	checkB : {},
        	checkB1 : {},
        	checkC : {},
        	checkD : {},
        	checkE : {}
        },
		temp: null
    },
    
    beforeCreate : function() {
    	var self = this;
    	
    	var url_enviar = jbase.getStringReplaced(jbase.urls.load_evento, [jbase.prop.id_evento]);
    	console.log("url_enviar : " + url_enviar);
    	//load data
    	jajax.apiAuthGet({
			url : url_enviar,
			success : function(data) {
				console.log(data);
				
				self.model = data;
				self.temp = JSON.stringify(data);
			}
		});
    },
    watch: {
    	"model.checkA.selectedBol": function() {
    		this.model.checkA.isSelected = this.model.checkA.selectedBol === true ? "1" : "0";
    	},
    	"model.checkB.selectedBol": function() {
    		this.model.checkB.isSelected = this.model.checkB.selectedBol === true ? "1" : "0";
    		
    		if(!this.model.checkB.selectedBol){
    			this.model.checkB1.selectedBol = false;
    			this.model.checkB1.isSelected = "0";
    		}
        },
        "model.checkB1.selectedBol": function() {
        	this.model.checkB1.isSelected = this.model.checkB1.selectedBol === true ? "1" : "0";
        },
        "model.checkC.selectedBol": function() {
        	this.model.checkC.isSelected = this.model.checkC.selectedBol === true ? "1" : "0";
        },
        "model.checkD.selectedBol": function() {
        	this.model.checkD.isSelected = this.model.checkD.selectedBol === true ? "1" : "0";
        },
        "model.checkE.selectedBol": function() {
        	this.model.checkE.isSelected = this.model.checkE.selectedBol === true ? "1" : "0";
        }
    },
    
    created : function() {
    	
    	
    },
    
    methods: {
    	load_data: function() {
    		
    	},
    	validar_reporte: function(evt) {
    		var self = this;
    		
    		if(!self.model.checkA.selectedBol && !self.model.checkB.selectedBol && !self.model.checkB1.selectedBol && !self.model.checkC.selectedBol && !self.model.checkD.selectedBol && !self.model.checkE.selectedBol){
    			jnoty.showMessage(jnoty.typeMessage.warning, "Seleccione al menos un evento de interrupción!", 2000);
    			return false;
    		}
    		
    		
    		if(JSON.stringify(self.model) === self.temp) { flujoRegistro.setStep('module/informe_reporte/'+jbase.prop.id_evento,'00'); return false; }//no cambia los valores - no update
    		//TODO CAMBIAR
    		//if(JSON.stringify(self.model) === self.temp) { flujoRegistro.setStep('module/informe_constancia/'+jbase.prop.id_evento,'00'); return false; }//para pruebas
    		
    		
    		jbase.wait(true);
    		jajax.apiAuthPost({
    			url : jbase.urls.validar_reporte,
    			data : self.model,
    			success : function(data) {
    				console.log(data);
    				jbase.wait(false);
    				
    				if(data.codigo == 0){
        				$("#div-modal_enviar").modal("toggle");
        				$("#enviar_message").html(data.message);
    				}else{
    					
    					var msg_alerta = "Se actualizará la información. ¿ Desea continuar ?";
    					
    					if(!self.model.checkA.idValidacion) { msg_alerta = "Se creará un evento de interrupción. <br/> ¿ Desea continuar ?" };
    					
    					jnoty.confirm(msg_alerta, function () { self.save_reporte(evt); } );
    				}
    			}
    		});
    	},
    	save_reporte: function(evt) {
    		var self = this;
    		
    		//self.model
    		
    		
    		var data_send = {
    			idEvento : jbase.prop.id_evento,
    			idEntidadVig : jbase.prop.id_ent_vig,
    			validaciones : self.model
    		};
    		
    		jbase.wait(true);
    		jajax.apiAuthPost({
    			url : jbase.urls.save_evento,
    			data : data_send,
    			success : function(data) {
    				console.log(data);
    				jbase.wait(false);
    				jnoty.showMessage(jnoty.typeMessage.success, "Se grabó correctamente", 2000);
    				flujoRegistro.setStep('module/informe_reporte/'+data.idEvento,'00');
    			}
    		});
    	}
    },
    
    updated: function() {
    	
    },
    
    mounted: function() {
    	
    	$("#app").show();
    }

});