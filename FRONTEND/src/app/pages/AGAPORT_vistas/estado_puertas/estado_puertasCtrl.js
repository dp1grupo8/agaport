(function () {
    'use strict';
  
    angular.module('Agaport.vistas.estado_puertas')
      .controller('estado_puertasCtrl', estado_puertasCtrl);
  
    /** @ngInject */
    function estado_puertasCtrl($scope) {
        
        $scope.range = function(num) {
            return new Array(num);   
        }
        
        //Lista de: 1,5,8,30 (puertas de vuelos que vienen), puertas habilitadas no en uso: 3,9,12,33
        //data importante para el estado
        /**
         * JSON: VueloLlegada.json
         * 
            * -estado //0: programado(vuelos en la cola de asignación). 2:asignado 3:aterrizado 4:muerto (vuelos que ya han liberado la puerta)
            *           Solo se obtendrán vuelos en estado 2 y 3
            * -Objeto: Puerta
            *      -idPuerta: corresponde al numero del gate/zona
            *      -distanciaSalida
            *      -flujoPersonas
            *      -tipo: 0:manga(gate), 1:zona
            *      -estado: 0: deshabilitada. 1: habilitada. 2: en uso. 3: programada
            * -Objeto: Avion
            *      -placa
            *      -aerolinea
            * -clase: clase del vuelo (string)
        */
    }
  
  })();