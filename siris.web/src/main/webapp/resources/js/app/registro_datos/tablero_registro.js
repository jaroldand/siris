
var app = new Vue({
    el: "#app",
    data: {
    	update_pagination: false,
    	
    	reporte_active : true,
    	historic_active : false,
        model: {
        	bandeja : []
        }
    },
    beforeCreate : function() {
    	var self = this;
    	
    	var url_enviar = jbase.getStringReplaced(jbase.urls.search_evento, [jbase.prop.id_ent_vig]);
    	
    	jajax.apiAuthGet({
			url : url_enviar,
			success : function(data) {
				console.log(data);
				
				self.model.bandeja = data;
				
				self.update_pagination = true;
			}
		});
    },
    created : function() {
    	
    },
    methods: {
    	showModuleForm: function(page, id_event) {
    		var self = this;
    		
    		flujoRegistro.setStep(page+"/"+id_event,'00');
    		
    	},
    	eliminar: function(id_event) {
    		var self = this;
    		jnoty.confirm("¿Está seguro de eliminar el borrador del evento de interrupción?", function () {self.deleteEvent(id_event); } );
    	},
    	deleteEvent: function(id_event) {
    		var self = this;
    		
    		jbase.wait(true);
    		jajax.apiAuthPost({
    			url : jbase.urls.delete_reporte,
    			data : id_event,
    			success : function(data) {
    				//pasar a la pagina de lectura
    				jbase.wait(false);
    				
    				jnoty.showMessage(jnoty.typeMessage.success, "El borrador del reporte fue eliminado correctamente", 3000);
    				
    				flujoRegistro.setStep("tablero_registro","P00");
    			}
    		});
    	}
    },
    updated: function() {
    	if(this.update_pagination){
    		
    		$("#myPager").html("");
    		$('#myTable').pageMe({
    			pagerSelector:'#myPager',
    			activeColor: 'blue',
    			prevText:'Anterior',
    			nextText:'Siguiente',
    			showPrevNext:true,
    			hidePageNumbers:false,
    			perPage:10
    		});
    		
    		this.model.bandeja.forEach(
        		/*function(e){
        			$("#"+ e.idEvento + "tipsbs").tooltip({ content : e.descCortaEvento });
        		}*/
				function(e){
        			$("#"+ e.idEvento + "tipsbs").tooltipster({
        			       maxWidth: 500,
        			       side: 'bottom',
        			       contentAsHTML: 'true'
        			    });
        		}
	        );
    		
    		this.update_pagination = false;
    	}
    },
    mounted: function() {
    	/*
    	var tooltips = $( ".tipsbs" ).tooltip({
    		position: {
    			my: "left top",
    			track:true,
    			trackTooltip:true,
    			at: "right+5 top-5",
    			collision: "none",
    			trackOrigin: true
    		}
    	});*/
    	
        $("#app").show();
        /*
        $('#myTable').pageMe({
			pagerSelector:'#myPager',
			activeColor: 'blue',
			prevText:'Anterior',
			nextText:'Siguiente',
			showPrevNext:true,
			hidePageNumbers:false,
			perPage:3
		  });
        */
    }

});