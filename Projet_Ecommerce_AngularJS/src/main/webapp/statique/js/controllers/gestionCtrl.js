monApp

.controller('gestionAllProdCtrl', function($scope, gestionFactory) {

	gestionFactory.getAllProd(function(callback) {
		$scope.allProd = callback;
	})
})

.controller(
		'gestionAllCatCtrl',
		function($rootScope, $scope, gestionFactory, $location) {

			// Obtenir toutes les catégories
			gestionFactory.getAllCat(function(callback) {
				$scope.allCat = callback;
			})

			// Obtenir les produits de la catégorie sélectionnée
			$scope.bouttonProduit = function(cat) {
				gestionFactory.getProdByCat(cat.id_categorie,
						function(callback) {
							$rootScope.prodByCat = callback;
						})
				$location.path('gestion/prodByCat')
			}

			// supprimer une catégorie
			$scope.bouttonDelete = function(cat) {
				gestionFactory.suppr(cat.nom, function(callback) {
					gestionFactory.getAllCat(function(callback) {
						if (callback != undefined && callback != "") {
							$scope.allCat = callback;
						}
					});
				});
			}

		})

.controller('gestionProdByCatCtrl',
		function($rootScope, $scope, gestionFactory, $location) {

			// Obtenir les produits d'une catégorie
			$scope.id = undefined;

			gestionFactory.getProdByCat($scope.id, function(callback) {
				$scope.prodByCat = callback;
			})

			$scope.bouttonDelete = function(prod) {
				gestionFactory.supprProd(prod.nom, function(callback) {
					$scope.prodByCat = callback;
				})
				gestionFactory.getProdByCat(function(callback) {
					$scope.prodByCat = callback;
				})
			}
			
			$rootScope.produit = {
					
					id_produit:-1,
					nom : "",
					description:"",
					quantite:"",
					prix:"",
					categorie:"",
				}
			
			$scope.boutonUpdate = function(prod){
				
				$rootScope.prod=prod;
//				$rootScope.produit.id_produit=prod.id_produit;
//				$rootScope.produit.nom=prod.nom;
//				$rootScope.produit.description=prod.description;
//				$rootScope.produit.quantite=prod.quantite;
//				$rootScope.produit.prix=prod.prix;
//				$rootScope.produit.categorie=prod.categorie;
				$location.path("gestion/updateProd")
			}

		})

.controller('gestionAddCatCtrl', function($scope, gestionFactory, $location) {

	$scope.categorie = {
		nom : ""
	}

	$scope.ajouterCat = function() {
		gestionFactory.addCat($scope.categorie, function(callback) {

			$location.path("gestion/allCat");

		});
	}

})

.controller('gestionAddProdCtrl', function($scope, gestionFactory, $location, $rootScope) {

	$scope.ajouterProd = function() {
		gestionFactory.addProd($scope.produit, function(callback) {

			$location.path("gestion/allCat");

		});
	}

})


.controller('gestionUpdateProdCtrl', function($scope, gestionFactory, $location, $rootScope){
		
				$scope.produit=$rootScope.prod;
//				$scope.produit.id_produit=$rootScope.prod.id_produit;
//				$scope.produit.nom=$rootScope.prod.nom;
//				$scope.produit.description=$rootScope.prod.description;
//				$scope.produit.quantite=$rootScope.prod.quantite;
//				$scope.produit.prix=$rootScope.prod.prix;
//				$scope.produit.categorie=$rootScope.prod.categorie;
			
				$scope.modifierProd = function(){
					gestionFactory.update($scope.produit, function(callback){

							$location.path("gestion/allCat");

					});
				}
	
		})

