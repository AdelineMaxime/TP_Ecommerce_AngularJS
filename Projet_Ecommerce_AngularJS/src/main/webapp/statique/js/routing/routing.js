monApp.config(function($routeProvider) {
	
	$routeProvider
	
	.when('/gestion/ident', {
		templateUrl: "partials/ident_Gestion.html",
		controller: "gestionIdentCtrl"
	})
	.when('/gestion/allCat', {
		templateUrl: "partials/allCat_Gestion.html",
		controller: "gestionAllCatCtrl"
	})
	.when('/gestion/allProd', {
		templateUrl: "partials/allProducts_Gestion.html",
		controller: "gestionAllProdCtrl"
	})
	.when('/gestion/prodByCat', {
		templateUrl: "partials/prodByCat_Gestion.html",
		controller: "gestionProdByCatCtrl"
	})
	.when('/gestion/addCat', {
		templateUrl: "partials/addCat.html",
		controller: "gestionAddCatCtrl"
	})
	.when('/gestion/addProd', {
		templateUrl: "partials/addProduct.html",
		controller: "gestionAddProdCtrl"
	})
	.when('/gestion/updateProd', {
		templateUrl: "partials/updateProd.html",
		controller: "gestionUpdateProdCtrl"
	})
	.when('/gestion/delProd', {
		templateUrl: "partials/deleteProd.html",
		controller: "gestionDelProdCtrl"
	})
	.when('/gestion/delCat', {
		templateUrl: "partials/deleteCat.html",
		controller: "gestionDelCatCtrl"
	})
	

	.when('/client/allProd', {
		templateUrl: "partials/allProducts_Client.html",
		controller: "clientAllProdCtrl"
	})
	.when('/client/allCat', {
		templateUrl: "partials/allCat_Client.html",
		controller: "clientAllCatCtrl"
	})
	.when('/client/panier', {
		templateUrl: "partials/panier.html",
		controller: "clientPanierCtrl"
	})
	.when('/client/ident', {
		templateUrl: "partials/ident_Client.html",
		controller: "clientIdentCtrl"
	})
	.when('/client/addClient', {
		templateUrl: "partials/addClient.html",
		controller: "clientaddClientCtrl"
	})

	
})