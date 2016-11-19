monApp


.controller('gestionAllProdCtrl', function($scope, gestionFactory) {

	gestionFactory.getAllProd(function(callback) {
		$scope.allProd = callback;
	})
})

.controller('gestionAllCatCtrl', function($rootScope, $scope, gestionFactory, $location) {

	// Obtenir toutes les catégories
	gestionFactory.getAllCat(function(callback) {
		$scope.allCat = callback;
	})

	// Obtenir les produits de la catégorie sélectionnée
	$scope.doGetProdByCat = function(cat) {
		gestionFactory.getProdByCat(cat.id_categorie, function(callback) {
			$rootScope.prodByCat = callback;
		})
		$location.path('gestion/prodByCat')
	}
	
	$scope.bouttonDelete = function(cat){
		gestionFactory.suppr(cat.nom, function(callback){
			gestionFactory.getAllCat(function(callback){
				if(callback != undefined && callback !=""){
					$scope.allCat=callback;
			}
			});
		});
	}

})