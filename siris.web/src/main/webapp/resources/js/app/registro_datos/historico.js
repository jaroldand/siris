var app = new Vue({
    el: "#app",
    data: {
    	historic_active : true,
    	reporte_active : false,
    	
    	update_pagination: false,
    	
    	//combos
    	anios_hist : [],
    	
        model: {
        	anio_select : null,
        	list_events_hist : []
        }
    },
    
    beforeCreate : function() {
    	
    },
    
    watch: {
    	
    },
    
    created : function() {
    	var self = this;
    	
    	//load anios hist
    	jajax.apiAuthGet({
			url : jbase.urls.anios_hist,
			success : function(data) {
				console.log(data);
				self.anios_hist = data;
				self.anios_hist.push( jbase.prop.valor_todos );
				
				self.model.anio_select = data[0];
				
				
				var url_enviar = jbase.getStringReplaced(jbase.urls.search_evento_hist, [jbase.prop.id_ent_vig, self.model.anio_select]);
				jajax.apiAuthGet({
					url : url_enviar,
					success : function(data) {
						console.log("historico data!");
						console.log(data);
						self.model.list_events_hist = data;
						
						self.update_pagination = true;
					}
				});
			}
		});
    	
    	
    },
    
    methods: {
    	showModuleForm: function(page, id_event) {
    		var self = this;
    		
    		flujoRegistro.setStep(page+"/"+id_event,'00');
    		
    	},
    	load_data: function() {
    		
    	},
    	obtenerHistoricoAnio: function() {
    		var self = this;
    		
    		jbase.wait(true);
    		var url_enviar = jbase.getStringReplaced(jbase.urls.search_evento_hist, [jbase.prop.id_ent_vig, self.model.anio_select]);
			jajax.apiAuthGet({
				url : url_enviar,
				success : function(data) {
					
					jbase.wait(false);
					self.update_pagination = true;
					
					self.model.list_events_hist = data;
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
    		
    		this.update_pagination = false;
    	}
    },
    
    mounted: function() {
    	
    	$("#app").show();
    	/*
    	$('#myTable').pageMe({
			pagerSelector:'#myPager',
			activeColor: 'blue',
			prevText:'Anterior',
			nextText:'Siguiente',
			showPrevNext:true,
			hidePageNumbers:false,
			perPage:10
		});*/
    }

});