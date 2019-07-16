(function () {
  'use strict';

  angular.module('Agaport.vistas.estado_puertas')
    .controller('estado_puertasCtrl', estado_puertasCtrl);

  /** @ngInject */
  function estado_puertasCtrl($scope, $http, $timeout) {

    $scope.simulacionIni = false;
    $scope.llamarSimulacion = function () {
      console.log('iniciando simulación 2');
      $http.get('http://200.16.7.178/backendAGAPORT/simulacion/iniciar').then(function successCallback(response) {
        //console.log('simulacion iniciada');
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    $scope.iniciarSimulacion = function () {
      if (!$scope.simulacionIni) {
        console.log('iniciando simulación 1');
        $scope.simulacionIni = true;
        $scope.llamarSimulacion();
        clearInterval($scope.simulacion);
        $scope.simulacion = setInterval($scope.leerData, 3000);
      }
    }

    $scope.detenerSimulacion = function () {
      if ($scope.simulacionIni) {
        $scope.simulacionIni = false;
        clearInterval($scope.simulacion);
        $scope.simulacion = setInterval($scope.leerData, 30000);
        console.log('detener simulación');
        $http.get('http://200.16.7.178/backendAGAPORT/simulacion/detener').then(function successCallback(response) {
          //console.log('simulacion iniciada');
        }, function errorCallback(response) {
          console.log(response);
        });
      }
    }

    $scope.$on('$destroy', function() {
      // detener la simulación si se cambia de pantalla
      $scope.detenerSimulacion();
    });

    $scope.progressFunction = function () {
      if (!$scope.simulacionIni) {
        return $timeout(function () { }, 30000);
      }
    };
    $scope.progressFunction2 = function () {
      if (!$scope.simulacionIni) {
        return $timeout(function () { }, 0);
      }
    };
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
        *      -distanciaASalida
        *      -flujoPersonas
        *      -tipo: 1:manga(gate), 0:zona
        *      -estado: 0: deshabilitada. 1: habilitada. 2: en uso. "3: programada (del vuelo)"
        * -Objeto: Avion
        *      -placa
        *      -aerolinea
        * -clase: clase del vuelo (string)
    */

    $scope.vuelos = [];
    $scope.puertas = [];
    $scope.urlImagen = [];

    $scope.leerData = function () {
      // console.log('leyendo data');
      // console.log($scope.vuelos);
      // console.log($scope.puertas);

      // Crear un array de url de imágenes para asignar a cada puerta
      // Por defecto es una imagen transparente pequeña
      for (var i = 0; i < 40; i++) {
        $scope.urlImagen[i] = '/../../../../assets/pictures/no-image.png';
      }
      // Crear un array de id de puertas que pueden existir
      $scope.puertasDisponibles = [];
      for (var i = 1; i <= 7; i++) {
        $scope.puertasDisponibles.push(i);
      }
      for (var i = 8; i <= 26; i++) {
        $scope.puertasDisponibles.push(i);
      }
      for (var i = 29; i <= 39; i++) {
        $scope.puertasDisponibles.push(i);
      }
      var divId = "";
      $http.get('http://200.16.7.178/backendAGAPORT/puertas/listarTodasPuertas').then(function successCallback(response) {
        // Hace que todas las puertas aparezcan de color gris

        // var div_zonas = document.getElementsByClassName("zona");
        // for (var i = 0; i < div_zonas.length; i++) {
        //   div_zonas[i].style.backgroundColor = "#E9E9E9";
        // }
        // var div_gates = document.getElementsByClassName("gate");
        // for (var i = 0; i < div_gates.length; i++) {
        //   div_gates[i].style.backgroundColor = "#E9E9E9";
        // }

        //Almacenar data de todas las puertas
        $scope.puertas = response.data;
        // console.log("data dentro de puertas: ");
        // console.log($scope.puertas);
        var puertas = $scope.puertas;
        // console.log("numero de puertas: " + puertas.length);
        var puerta;
        var tipo;
        for (var i = 0; i < puertas.length; i++) {
          puerta = puertas[i];
          if ($scope.puertasDisponibles.includes(puerta.idPuerta) > 0) {
            if (puerta.tipo == 1) {
              tipo = 'gate';
            } else {
              tipo = 'zona';
            }
            divId = tipo + '-' + puerta.idPuerta;
            if (puerta.estado == 1) {
              // Pintar puerta de color blanco
              // console.log(divId + " blanco");
              document.getElementById(divId).style.backgroundColor = "#FFFFFF";
            }
            if (puerta.estado == 0) {
              // Pintar puerta de color gris
              // console.log(divId + " gris");
              document.getElementById(divId).style.backgroundColor = "#E9E9E9";
            }
          } else {
            //console.log("puerta: " + puerta.idPuerta + " no existe");
          }
        }
        
        $scope.pintarPuertasUsadas();
        // console.log('fin: leer data')
      }, function errorCallback(response) {
        console.log(response);
      });      
    }

    //función que llama a otra función cada 60 seg
    $scope.leerData();
    $scope.simulacion = setInterval($scope.leerData, 30000);

    //pintar puertas amarillas y aviones
    $scope.pintarPuertasUsadas = function () {
      $http.get('http://200.16.7.178/backendAGAPORT/VuelosLlegada/listar').then(function successCallback(response) {
        //Almacena data de todos los vuelos no "muertos"
        $scope.vuelos = response.data;
        // console.log("data dentro de vuelos: ");
        // console.log($scope.vuelos);
        var vuelos = $scope.vuelos;
        var puerta;
        var tipo;
        for (var i = 0; i < vuelos.length; i++) {
          puerta = vuelos[i].puerta;
          if ($scope.puertasDisponibles.includes(puerta.idPuerta) > 0) {
            tipo;
            if (puerta.tipo == 1) {
              tipo = 'gate';
            } else {
              tipo = 'zona';
            }
            var divId = tipo + '-' + puerta.idPuerta;
            if (vuelos[i].estado == 3) {
              // console.log(divId + " dibujar avion");
              var src = document.getElementById(divId);
              src.style.backgroundColor = "#F1C232";
              //agregar icono de avion
              $scope.urlImagen[puerta.idPuerta] = '/../../../../assets/pictures/aiga_departingflights-512.png'
            } else {
              // console.log(divId + " color naranja");
              document.getElementById(divId).style.backgroundColor = "#F1C232";
            }
          } else {
            console.log("puerta: " + puerta.idPuerta + " no existe");
          }
        }
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    $scope.detalle = function (tipo, idPuerta) {
      $scope.puertaSeleccionada = true;
      $scope.puerta = $scope.vuelos.filter(function (e) { return e.puerta.idPuerta === idPuerta; })[0];
      if ($scope.puerta == undefined) {
        $scope.puerta = $scope.puertas.filter(function (e) { return e.idPuerta === idPuerta; })[0];
        console.log($scope.puerta);

        switch ($scope.puerta.tipo) {
          case 0:
            $scope.puerta.strTipo = 'Zona';
            break;
          case 1:
            $scope.puerta.strTipo = 'Gate';
            break;
        }

        switch ($scope.puerta.estado) {
          case 0:
            $scope.puerta.strEstadoPuerta = 'Deshabilitado';
            break;
          case 1:
            $scope.puerta.strEstadoPuerta = 'Habilitado';
            break;
        }

        if ($scope.puerta.distanciaASalida !== undefined) {
          $scope.puerta.strDistancia = $scope.puerta.distanciaASalida + ' ' + 'metros';
        } else {
          $scope.puerta.strDistancia = '';
        }
        if ($scope.puerta.flujoPersonas !== undefined) {
          $scope.puerta.strFlujo = $scope.puerta.flujoPersonas + ' ' + 'personas por minuto';
        } else {
          $scope.puerta.strFlujo = '';
        }

      } else {
        //console.log($scope.puerta);

        switch ($scope.puerta.puerta.tipo) {
          case 0:
            $scope.puerta.strTipo = 'Zona';
            break;
          case 1:
            $scope.puerta.strTipo = 'Gate';
            break;
        }

        switch ($scope.puerta.estado) {
          case 2:
            $scope.puerta.strEstadoPuerta = 'Uso programado';
            break;
          case 3:
            $scope.puerta.strEstadoPuerta = 'En uso';
            break;
        }

        if ($scope.puerta.puerta.distanciaASalida !== undefined) {
          $scope.puerta.strDistancia = $scope.puerta.puerta.distanciaASalida + ' ' + 'metros';
        } else {
          $scope.puerta.strDistancia = '';
        }
        if ($scope.puerta.puerta.flujoPersonas !== undefined) {
          $scope.puerta.strFlujo = $scope.puerta.puerta.flujoPersonas + ' ' + 'personas por minuto';
        } else {
          $scope.puerta.strFlujo = '';
        }
      }
    }

  }

})();