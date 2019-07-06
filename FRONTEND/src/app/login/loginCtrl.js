(function () {
    'use strict';
  
    angular.module('BlurAdmin.login')
        .controller('LoginCtrl', LoginCtrl);
  
    /** @ngInject */
    function LoginCtrl($scope,$http) {
  
      $scope.smartTablePageSize = 10;
      $scope.usuarioSeleccionado=[];
      
      $scope.datosUsuarios='';

      $scope.logeo = function (){
          console.log("clickeaste");
      };

    }

  
  })();
  