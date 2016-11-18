monApp

.controller('clientAllCatCtrl', function($rootScope, $scope, clientFactory, $location) {


	
	
	clientFactory.getAllCat(function(callback) {
		$scope.allCat = callback;
		nb = callback.lenght;
	})

	
	
	
	$scope.doGetProdByCat = function(cat) {
		clientFactory.getProdByCat(cat.id_categorie, function(callback) {
			$rootScope.prodByCat = callback;
		})
		$location.path('client/prodByCat')
	}

})

.controller('clientAllProdCtrl', function($scope, clientFactory) {

	clientFactory.getAllProd(function(callback) {
		$scope.allProd = callback;
	})
})

.controller('clientProdByCatCtrl', function($scope, clientFactory) {

	$scope.id = undefined;
	$scope.doGetProdByCat = function() {
		clientFactory.getProdByCat($scope.id, function(callback) {
			$scope.prodByCat = callback;
		})
	}
})
