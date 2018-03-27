 app.directive('fileModel', function (httpPostFactory) {
    return {
        restrict: 'A',
        scope: true,
        link: function (scope, element, attr) {

            element.bind('change', function () {
                //return console.log(element[0].name);
                var formData = new FormData();
                formData.append('file', element[0].files[0]);
                formData.append('garantias_service', 1);
                formData.append('siniestro',$("input[name='idsiniestro']").val());
                formData.append("tipo_ima",element[0].name);
                httpPostFactory('http://app.aoacolombia.com/Control/operativo/controllers/RecepcionController.php', formData, function (callback) {
                   // recieve image name to use in a ng-src 
                    console.log(callback);
                });
            });

        }
    };
});
