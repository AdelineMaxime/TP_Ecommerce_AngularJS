monApp.factory('clientFactory', function($http) {
	
	var urlString = "http://localhost:8080/Projet_Ecommerce_AngularJS/client";
	
	function getAllProd(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allProducts'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log('Erreur: '+response.statusText);
		});
	}
	
	
	function getAllCat(callback) {
		$http({
			method: 'GET',
			url: urlString+'/allCat'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function getProdByCat(id,callback) {
		$http({
			method: 'GET',
			url: urlString+'/prodByCat/'+id
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	
	function addPanier(nomProd,callback) {
		$http({
			method: 'GET',
			url: urlString+'/addPanier/'+nomProd
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	
	function getPanier(callback) {
		$http({
			method: 'GET',
			url: urlString+'/panier'
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function(response) {
			console.log(response);
		});
	}
	
	
	return {
		getAllProd:getAllProd,
		getAllCat:getAllCat,
		getProdByCat: getProdByCat,
		addPanier: addPanier,
		getPanier: getPanier,
	}
	
})