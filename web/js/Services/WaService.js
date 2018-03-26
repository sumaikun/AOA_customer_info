app.factory('WaService',['$http','$q',function($http,$q){
	
	var WaService = {};
        var defered = $q.defer();
        var promise = defered.promise;

   

	WaService.credit_warranty = function(data){

		return $http.post("credit_warranty",data);		

	}
        
        WaService.devol_data = function(data){

		return $http.post("devol_data",data);		

	}
	
	
	return WaService;	
	
}]);
