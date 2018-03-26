app.controller('WaController',['$scope','WaService','$window',function($scope,WaService,$window){
	
	$scope.current_chart = {} ;

	$scope.title = 'Demo';
        
        $scope.type_warranty = "";
        
        $scope.warranty = {};

        $scope.garantia_consignada = function(){
            //alert("working");
        }
        
        $scope.valor_no_reembol = function(){
            //alert("working");
        }
        
        $scope.garantia_credito = function(){
            //alert("working");
        }
        
        $scope.datos_devoluciones = function(){
            //alert("working");
        }
        
        $scope.datos_devolucion_save = function(){
            if($("input[name='devol_cuenta_bancaria']").val() == "")
            {
                    alert("Ingrese el numero de cuenta bancaria de devoluciones");
                    return false;
            }
            if($("input[name='devol_cuenta_bancaria']").val().length<7 || $("input[name='numero_tarjeta']").length>16)
            {
                    console.log($("input[name='devol_cuenta_bancaria']").val());
                    alert("El numero de la cuenta no debe ser mayor de 16 dígitos o menor de 7 dígitos");
                    return false;
            }	

            if($("select[name='devol_banco']").val() == "")
            {
                    alert("Seleccione el banco de devolucion");
                    return false;
            }

            if($("input[name='devol_nombre_titular']").val() == "")
            {
                    alert("Ingrese el nombre de titular de devolución");
                    return false;
            }

            if($("input[name='devol_iden_titular']").val() == "")
            {
                    alert("Ingrese el documento de identidad de titular de devolución");
                    return false;
            }

            if($("input[name='devol_iden_titular']").val().length<7 || $("input[name='numero_tarjeta']").length>10)
            {
                    alert("El numero del documento de identidad debe estar entre los 7 a 10 dígitos");
                    return false;
            }
            console.log($scope.warranty);
             var request = WaService.devol_data($scope.warranty);
		request.then(function(response){
			
			
		});
        }
	
        $scope.verifywarranty = function(tipo)
        {
            console.log("tipo "+tipo);
            $scope.type_warranty = tipo;
            if(tipo == "credito")
            {
                console.log($("select[name='year_expi']").val());
                var d = new Date();
                var y = d.getFullYear();
                var m = d.getMonth();
                
               

                if($("select[name='year_expi']").val() == y)
                {
                    if($("select[name='month_expi']").val()<=m)
                    {
                            alert("fecha de vencimiento invalida");
                            return false;
                    }
                }

                if($("select[name='year_expi']").val() < y)
                {
                        alert("El año de vencimiento ya paso");
                        return false;
                }

                if($("input[name='numero_tarjeta']").val().length != 16 )
                {
                        console.log($("input[name='numero_tarjeta']").val().length);
                        alert("El numero de la tarjeta no debe ser mayor o menor de 16 dígitos");
                        return false;
                }		

                if($("input[name='cvv']").val().length != 3)
                {
                        alert("El cvv debe tener tres dígitos");
                        return false;
                }

                if($("select[name='banco']").val() == "")
                {
                        alert("Seleccione el banco de la tarjeta de credito");
                        return false;
                }

                if($("select[name='franquicia']").val() == "")
                {
                        alert("Seleccione la franquicia de la tarjeta de credito");
                        return false;
                }
                
                if($("select[name='year_expi']").val() == '? undefined:undefined ?')
                {
                        alert("Seleccione el mes de año de la tarjeta de credito");
                        return false;
                }	

                if($("select[name='month_expi']").val() == '? undefined:undefined ?')
                {
                        alert("Seleccione el mes de expiración de la tarjeta de credito");
                        return false;
                }
                alert("Proceda a ingresar los datos de devolución para empezar su proceso de aprobeación de garantia");
                
                $("#myModal").modal('hide');
            }
            if(tipo == "efectivo")
            {
                if($("input[name='comprobante_consignacion']").val().length < 7 || $("input[name='comprobante_consignacion']").val().length > 15){
                        alert("El comprobante de consignación debe tener entre 7 a 15 dígitos");
                        return false;
                }
                if($("input[name='fecha_consignacion']").val() == ""){
                        alert("Debe agregar una fecha de consignación");
                        return false;
                }
                    $("#myModal2").modal('hide');
            }
            if(tipo == "riesgo")
            {
                if($("input[name='riesgo_consignacion']").val().length < 7 || $("input[name='riesgo_consignacion']").val().length > 15){
                        alert("El comprobante de consignación debe tener entre 7 a 15 dígitos");			
                        return false;
                }
                if($("input[name='riesgo_fecha']").val() == ""){
                        alert("Debe agregar una fecha de consignación");
                        return false;
                }	
              
                    $("#myModal3").modal('hide');		

            }
            console.log($scope.warranty);
            return true;
            
        }
        
        

	function verify_destroy_session(str)
	{		
		if(typeof str !== 'object')
		{
			$window.location.reload();	
		}
		
	}        

}]);