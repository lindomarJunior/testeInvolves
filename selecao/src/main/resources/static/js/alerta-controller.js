angular.module("alertaApp").controller("AlertaController", function($scope, $http) {
	$http({
        method : "GET",
        url : "http://localhost:8080/alertas"
    }).then(function mySuccess(response) {
        $scope.listaAlerta = response.data;
    }, function myError(response) {
    	console.log(response.data);
    	console.log(response.status);
        console.log(response.statusText);
    });
});