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
	
	function deletePanier(nomProd,callback) {
		$http({
			method: 'GET',
			url: urlString+'/deletePanier/'+nomProd
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function clientExist(nomCl, passwordCl, callback) {
		$http({
			method: 'POST',
			url: urlString+'/connecterClient/'+nomCl+'/'+passwordCl
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function finaliser(nomCl, callback) {
		$http({
			method: 'GET',
			url: urlString+'/final/'+nomCl
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function addClient(client, callback) {
		$http({
			method: 'POST',
			url: urlString+'/addClient',
			data:angular.toJson(client),
			headers:{
				'Content-Type':'application/json'
			}
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function getCommandes(client, callback) {
		$http({
			method: 'POST',
			url: urlString+'/commandes',
			data: angular.toJson(client),
			headers:{
				'Content-Type':'application/json'
			}
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	function getOldPanier(commande, callback) {
		$http({
			method: 'POST',
			url: urlString+'/oldPanier',
			data: angular.toJson(commande),
			headers:{
				'Content-Type':'application/json'
			}
		}).success(function(response) {
			console.log(response);
			callback(response);
		}).error(function (response) {
			console.log(response);
		});
	}
	
	return {
		getAllProd:getAllProd,
		getAllCat:getAllCat,
		getProdByCat: getProdByCat,
		addPanier: addPanier,
		getPanier: getPanier,
		deletePanier: deletePanier,
		clientExist: clientExist,
		finaliser: finaliser,
		addClient: addClient,
		getCommandes: getCommandes,
		getOldPanier: getOldPanier,
	}
	
})