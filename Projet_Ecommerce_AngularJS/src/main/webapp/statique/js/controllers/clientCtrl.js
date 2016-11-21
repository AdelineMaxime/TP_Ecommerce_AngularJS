monApp

.controller('clientAllCatCtrl', function($rootScope, $scope, clientFactory, $location) {

			// Obtenir toutes les catégories
			clientFactory.getAllCat(function(callback) {
				$scope.allCat = callback;
			})

			// Obtenir les produits de la catégorie sélectionnée
			$scope.doGetProdByCat = function(cat) {
				clientFactory.getProdByCat(cat.id_categorie,
						function(callback) {
							$rootScope.prodByCat = callback;
						})
				$location.path('client/prodByCat')
			}

			// Aller au panier
			$rootScope.panier = {
				'id_panier' : undefined,
				'prixTotal' : undefined,
				'clientP' : undefined,
				'listeLC' : undefined
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
			$scope.doAddPanier = function(prod) {
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

	// Supprimer du panier
	$scope.doDeletePanier = function(prod) {
		clientFactory.deletePanier(prod.nom, function(callback) {
			$scope.panier = callback;
		})
		clientFactory.getPanier(function(callback) {
			$scope.panier = callback;
		})
	}
})

.controller('clientIdentCtrl', function($rootScope, $scope, clientFactory, $location) {

			$scope.nom = undefined;
			$scope.password = undefined;

			$scope.doClientExist = function(nom, password) {

				console.log("-----------------------------------------------"+nom);
				
				clientFactory.clientExist(nom, password, function(callback) {
					$scope.client = callback;
					

					if ($scope.client.nom == undefined) {

					} else {
						$rootScope.client = $scope.client;
						$location.path('client/valid');
					}
				})

			}
		})
		
.controller('clientValidCommCtrl', function($rootScope, $scope, clientFactory, $location) {

	
	clientFactory.getPanier(function(callback) {
		$scope.panier = callback;
	})
	
	$scope.nom = $scope.panier.clientP.nom;
	$scope.doFinaliser = function(nom) {
		clientFactory.finaliser(nom, function(callback) {
			$location.path('client/allCat');
		})
	}
	
	
})

