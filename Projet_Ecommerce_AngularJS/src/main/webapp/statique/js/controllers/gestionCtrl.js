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
	$scope.bouttonProduit = function(cat) {
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

.controller('gestionProdByCatCtrl', function($rootScope, $scope, gestionFactory, $location) {

	// Obtenir les produits d'une cétgorie
	$scope.id = undefined;
	$scope.bouttonProduit = function() {
		gestionFactory.getProdByCat($scope.id, function(callback) {
			$scope.prodByCat = callback;
		})
	}
	
	$scope.bouttonDelete = function(prod){
		gestionFactory.supprProd(prod.nom, function(callback){
			gestionFactory.getAllCat(function(callback){
				if(callback != undefined && callback !=""){
					$scope.allCat=callback;
			}
			});
		});
		$location.path('gestion/prodByCat')
	}
	
})

