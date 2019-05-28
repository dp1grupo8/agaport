(function () {
    'use strict';
  
    angular.module('Agaport.vistas.asignacion_vuelos')
      .controller('asignacion_vuelosCtrl', asignacion_vuelosCtrl);
  
    /** @ngInject */
    function asignacion_vuelosCtrl($scope) {
        
        $scope.range = function(num) {
            return new Array(num);   
        }
        
    }
  
  })();