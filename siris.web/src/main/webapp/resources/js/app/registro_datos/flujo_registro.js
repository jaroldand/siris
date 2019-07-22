window.flujoRegistro = window.flujoRegistro || {};

(function($f) {

    $f.init = function() {};

    $f.setStep = function(url, step) {
    	
        jbase.getContentLoadSecondary(url, step);
    };
}(window.flujoRegistro));

// $(document).ready(function() {
//     base.getContentSecondary("../app/registro_datos/datos_representante.html");
// });