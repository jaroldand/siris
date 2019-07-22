var app = new Vue({
    el: "#app",
    data: {
    	model: {
        }
    },
    
    beforeCreate : function() {
    	var self = this;
    	var page_inicio;
    	
    	/*jajax.getAuthHtml({
			url : jbase.urls.inicio,
			success : function(data) {
				page_inicio = data;
	        	flujoRegistro.setStep(page_inicio,"P00");
			}
		});*/
    	
    	page_inicio = "tablero_registro";
    	flujoRegistro.setStep(page_inicio,"P00");
    },
    methods: {
    	load: function() {
    		
    	}
    },
    mounted: function() {
    	this.load();
    	//$("#app").show();
    }

});