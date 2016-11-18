monApp

.controller('clientAllCatCtrl', function($rootScope, $scope, clientFactory, $location) {

	// Obtenir toutes les catégories
	clientFactory.getAllCat(function(callback) {
		$scope.allCat = callback;
	})

	// Obtenir les produits de la catégorie sélectionnée
	$scope.doGetProdByCat = function(cat) {
		clientFactory.getProdByCat(cat.id_categorie, function(callback) {
			$rootScope.prodByCat = callback;
		})
		$location.path('client/prodByCat')
	}
	
	
	// Aller au panier
	$rootScope.panier = {
			'id_panier':undefined,
			'prixTotal':undefined,
			'clientP':undefined,
			'listeLC':undefined
	}
	$scope.goPanier = function() {
		clientFactory.getPanier(function(callback) {
			$rootScope.panier = callback;
			$location.path('client/panier');
		})
	}
	
	

})

.controller('clientAllProdCtrl', function($scope, clientFactory) {

	clientFactory.getAllProd(function(callback) {
		$scope.allProd = callback;
	})
})

.controller('clientProdByCatCtrl', function($rootScope, $scope, clientFactory, $location) {

	// Obtenir les produits d'une cétgorie
	$scope.id = undefined;
	$scope.doGetProdByCat = function() {
		clientFactory.getProdByCat($scope.id, function(callback) {
			$scope.prodByCat = callback;
		})
	}
	
	
	// Ajouter au panier
	$scope. doAddPanier = function(prod) {
		clientFactory.addPanier(prod.nom, function(callback) {
			$rootScope.panier = callback;
		})
		
		$location.path('client/allCat');
	}
	
})

.controller('clientPanierCtrl', function($scope, clientFactory) {
	
	clientFactory.getPanier(function(callback) {
		$scope.panier = callback;
	})
})


