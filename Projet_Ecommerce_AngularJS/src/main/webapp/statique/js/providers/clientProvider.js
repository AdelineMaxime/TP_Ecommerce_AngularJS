monApp.factory('clientFactory', function($http) {
	
	var urlString = "http://localhost:8080/Projet_Ecommerce_AngularJS/client";
	
	function getAllProd(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allProducts'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (reason) {
			console.log('Erreur : '+reason);
		})
	}
	
	
	function getAllCat(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allCat'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (reason) {
			console.log('Erreur : '+reason);
		})
	}
})