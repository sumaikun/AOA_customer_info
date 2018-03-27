app.factory('WaService',['$http','$q',function($http,$q){
	
	var WaService = {};
        var defered = $q.defer();
        var promise = defered.promise;

   

	WaService.credit_warranty = function(data){

		return $http.post("credit_warranty",data);		

	}
        
        WaService.consigment_warranty = function(data){

		return $http.post("consignment_warranty",data);		

	}
        
        WaService.risk_warranty = function(data){

		return $http.post("risk_warranty",data);		

	}
        
        WaService.devol_data = function(data){

		return $http.post("devol_data",data);		

	}
        
       
        
        WaService.get_images = function(data){
            console.log(data);
            var formData = new FormData();
            formData.append('sid', data.sid);
            return $http({
                url: "http://app.aoacolombia.com/Control/operativo/controllers/RecepcionController.php",
                method: "POST",
                data: formData,
                headers: {
                  'Content-Type': undefined
                }
              });	
        }
	
        
	
	return WaService;	
	
}]);

app.factory('httpPostFactory', function($http, $rootScope) {
  return function(file, data, callback) {
    $http({
      url: file,
      method: "POST",
      data: data,
      headers: {
        'Content-Type': undefined
      }
    }).then(function(response) {
        var divid = document.getElementById('angular-div');
        console.log(divid);
        angular.element(divid).scope().erase_img();
        //angular.element(divid).scope().get_images();
       //get_images();
      callback(response);
    });
  };
});