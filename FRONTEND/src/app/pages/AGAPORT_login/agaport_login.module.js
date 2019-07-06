(function () {
    'use strict';
  
    angular.module('Agaport.login', [
        'ui.select',
        'ngSanitize',
      ])
        .config(routeConfig);
  
    /** @ngInject */
    function routeConfig($stateProvider) {
      $stateProvider
          .state('agaport_login', {
            url: 'auth.html',
            templateURL : 'auth.html',
            controller: 'LoginCtrl',
          });
    }
  
  })();
  