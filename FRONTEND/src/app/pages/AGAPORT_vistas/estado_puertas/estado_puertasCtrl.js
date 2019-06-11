(function () {
  'use strict';

  angular.module('Agaport.vistas.estado_puertas')
    .controller('estado_puertasCtrl', estado_puertasCtrl);

  /** @ngInject */
  function estado_puertasCtrl($scope, $http) {

    $scope.puertaSeleccionada = false;

    $scope.range = function (num) {
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
    // $('document').ready(function () {
    //   var div_zonas = document.getElementsByClassName("zona");
    //   for (var i = 0; i < div_zonas.length; i++) {
    //     div_zonas[i].style.backgroundColor = "#E9E9E9";
    //   }
    //   var div_gates = document.getElementsByClassName("gate");
    //   for (var i = 0; i < div_gates.length; i++) {
    //     div_gates[i].style.backgroundColor = "#E9E9E9";
    //   }
    // });

    $scope.vuelos = [];
    $scope.puertas = [];
    $scope.urlImagen = [];

    $scope.leerData = function () {
      // console.log('leyendo data');
      // console.log($scope.vuelos);
      // console.log($scope.puertas);
      for (var i = 0; i < 40; i++) {
        $scope.urlImagen[i] = '/../../../../assets/pictures/no-image.png';
      }
      $http.get('http://demo4498234.mockable.io/puertasAsignadas').then(function successCallback(response) {
        // var div_zonas = document.getElementsByClassName("zona");
        // for (var i = 0; i < div_zonas.length; i++) {
        //   div_zonas[i].style.backgroundColor = "#E9E9E9";
        // }
        // var div_gates = document.getElementsByClassName("gate");
        // for (var i = 0; i < div_gates.length; i++) {
        //   div_gates[i].style.backgroundColor = "#E9E9E9";
        // }
        $scope.puertas = response.data;
        var puertas = $scope.puertas;
        for (var i = 0; i < puertas.length; i++) {
          var puerta = puertas[i].Puerta;
          var tipo;
          if (puerta.tipo == 0) {
            tipo = 'gate';
          } else {
            tipo = 'zona';
          }
          var divId = tipo + '-' + puerta.idPuerta;
          if (puerta.estado == 1) {
            document.getElementById(divId).style.backgroundColor = "#FFFFFF"
          }
          if (puerta.estado == 0) {
            document.getElementById(divId).style.backgroundColor = "#E9E9E9"
          }
        }
      }, function errorCallback(response) {
        console.log(response);
      });
      $http.get('/app/pages/AGAPORT_vistas/estado_puertas/data/vuelos-llegada.json').then(function successCallback(response) {
        $scope.vuelos = response.data;
        var vuelos = $scope.vuelos;
        for (var i = 0; i < vuelos.length; i++) {
          var puerta = vuelos[i].Puerta;
          var tipo;
          if (puerta.tipo == 0) {
            tipo = 'gate';
          } else {
            tipo = 'zona';
          }
          var divId = tipo + '-' + puerta.idPuerta;
          if (puerta.estado == 2) {
            var src = document.getElementById(divId);
            src.style.backgroundColor = "#F1C232";
            //agregar icono de avion
            $scope.urlImagen[puerta.idPuerta] = '/../../../../assets/pictures/aiga_departingflights-512.png'
          } else {
            document.getElementById(divId).style.backgroundColor = "#F1C232";
          }
        }
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    //función que llama a otra función cada 30 seg
    $scope.leerData();
    setInterval($scope.leerData, 30000);

    $scope.detalle = function (tipo, idPuerta) {
      $scope.puertaSeleccionada = true;
      $scope.puerta = $scope.vuelos.filter(function (e) { return e.Puerta.idPuerta === idPuerta; })[0];
      if ($scope.puerta == undefined) {
        $scope.puerta = $scope.puertas.filter(function (e) { return e.Puerta.idPuerta === idPuerta; })[0];
      }
      // console.log($scope.puerta);

      switch ($scope.puerta.Puerta.tipo) {
        case 0:
          $scope.puerta.strTipo = 'Gate';
          break;
        case 1:
          $scope.puerta.strTipo = 'Zona';
          break;
      }

      switch ($scope.puerta.Puerta.estado) {
        case 0:
          $scope.puerta.strEstadoPuerta = 'Deshabilitado';
          break;
        case 1:
          $scope.puerta.strEstadoPuerta = 'Habilitado';
          break;
        case 2:
          $scope.puerta.strEstadoPuerta = 'En uso';
          break;
        case 3:
          $scope.puerta.strEstadoPuerta = 'Uso programado';
          break;
      }

      if ($scope.puerta.distanciaSalida !== undefined) {
        $scope.puerta.strDistancia = $scope.puerta.distanciaSalida + ' ' + 'metros';
      } else {
        $scope.puerta.strDistancia = '';
      }
      if ($scope.puerta.flujoPersonas !== undefined) {
        $scope.puerta.strFlujo = $scope.puerta.flujoPersonas + ' ' + 'personas por minuto';
      } else {
        $scope.puerta.strFlujo = '';
      }

    }

    $scope.avionEstacionado = function (idPuerta) {
      return $scope.vuelos.filter(function (e) { return (e.Puerta.idPuerta === idPuerta) && (e.Puerta.estado == 2); }).length > 0;
    }
  }

})();