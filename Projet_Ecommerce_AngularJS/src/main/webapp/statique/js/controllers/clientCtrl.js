monApp

.controller('clientAllCatCtrl', function($scope, clientFactory) {
	
	clientFactory.getAllCat (function(callback) {
		$scope.allCat = callback;
	})
})

.controller('clientAllProdCtrl', function($scope, clientFactory) {
	
	clientFactory.getAllProd (function(callback) {
		$scope.allProd = callback;
	})
})