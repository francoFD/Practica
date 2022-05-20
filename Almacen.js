var txt_Fideos = localStorage.getItem("ls_Fideos");
if (txt_Fideos) {
    var Fideos = JSON.parse(txt_Fideos);
} else {
    var Fideos = [];
}

var txt_Cantidad = localStorage.getItem("ls_Cantidad");
if (txt_Cantidad) {
    var Cantidad = JSON.parse(txt_Cantidad);
} else {
    var Cantidad = [];
}

var txt_Precio = localStorage.getItem("ls_Precio");
if (txt_Precio) {
    var Precio = JSON.parse(txt_Precio);
} else {
    var Precio = [];
}

var txt_Marca = localStorage.getItem("ls_Marca");
if (txt_Marca) {
    var Marca = JSON.parse(txt_Marca);
} else {
    var Marca = [];
}

var txt_Clientes = localStorage.getItem("ls_Clientes");
if (txt_Clientes) {
    var Clientes = JSON.parse(txt_Clientes);
} else {
    var Clientes = [];
}

var txt_misClien = localStorage.getItem("ls_misClien");
if (txt_misClien) {
    var misClien = JSON.parse(txt_misClien);
} else {
    var misClien = [];
}

var txt_misProduc = localStorage.getItem("ls_misProduc");
if (txt_misProduc) {
    var misProduc = JSON.parse(txt_misProduc);
} else {
    var misProduc = [];
}

var txt_misCant = localStorage.getItem("ls_misCant");
if (txt_misCant) {
    var misCant = JSON.parse(txt_misCant);
} else {
    var misCant = [];
}

Listado();

function Listado() {
    var Ls = "";
    for (i = 0; i < Fideos.length; i++) {
        Ls = Ls + "<tr><td>" + [i] + " </td> <td>" + Fideos[i] + "</td><td>" + Cantidad[i] + "</td><td>" + Precio[i] + "</td><td>" + Marca[i] + "</td></tr>";
    };
    Ls = "<table class=table table-sm><tr><th>N°</th><th>  Fideos  </th><th> Cantidad  </th><th> Precio </th><th> Marca </th></tr>" + Ls + "</table>";
    document.getElementById("Exit").innerHTML = Ls;

    localStorage.setItem("ls_Fideos", JSON.stringify(Fideos));
    localStorage.setItem("ls_Cantidad", JSON.stringify(Cantidad));
    localStorage.setItem("ls_Precio", JSON.stringify(Precio));
    localStorage.setItem("ls_Marca", JSON.stringify(Marca));
}

function VerClientes() {
    var Regis = "";
    for (i = 0; i < Clientes.length; i++) {
        Regis = Regis + "<p>" + i + " - " + Clientes[i] + "</p>";
    }
    document.getElementById("salida1").innerHTML = Regis;

    localStorage.setItem("ls_Clientes", JSON.stringify(Clientes));
}

function AgregarCliente() {
    var miNombre = document.getElementById("Usuarios").value;
    Clientes.push(miNombre);
    VerClientes();
}

function Agregado() {
    var N_Fideos = document.getElementById("Mercaderia").value;
    var N_Cantidad = document.getElementById("Unidades").value;
    N_Cantidad=N_Cantidad*1;
    var N_Precio = document.getElementById("Valor").value;
    N_Precio=N_Precio*1;
    var N_Marca = document.getElementById("NomMarca").value;

    Fideos.push(N_Fideos);
    Cantidad.push(N_Cantidad);
    Precio.push(N_Precio);
    Marca.push(N_Marca);

    Listado();
}

function Comprar() {
    var Persona = document.getElementById("Nombres").value;
    var idProd = document.getElementById("Producto").value;
    var Cant = document.getElementById("Unid").value;

    for (var i = 0; i < Clientes.length; i++) {
        if (Clientes[i] == Clientes[Persona]) {
            var Per = Clientes[i];
        }
    }
    for (var i = 0; i < Fideos.length; i++) {
        if (Fideos[i] == Fideos[idProd]) {
            var Fid = Fideos[i];
        }
    }
    Cantidad[idProd] = Cantidad[idProd] - Cant;

    misClien.push(Per);
    misProduc.push(Fid);
    misCant.push(Cant);

    localStorage.setItem("ls_misClien", JSON.stringify(misClien));
    localStorage.setItem("ls_misProduc", JSON.stringify(misProduc));
    localStorage.setItem("ls_misCant", JSON.stringify(misCant));

    Listado();
}

function Ver() {
    var Com = "";
    for (var i = 0; i < misClien.length; i++) {
        Com = Com + "<p>" + i + ") " + misClien[i] + " - " + misProduc[i] + " - " + misCant[i] + "</p>";
    }
    document.getElementById('salida').innerHTML = Com;
}

function EliminarUno() {
    var paraEliminar = document.getElementById("NumFide").value;
    Fideos.splice(paraEliminar, 1);
    Cantidad.splice(paraEliminar, 1);
    Precio.splice(paraEliminar, 1);
    Marca.splice(paraEliminar, 1);
    Listado();
}

function VerUno() {
    var Escri = "";
    var verF = document.getElementById("ConsUno").value;

    for (var i = 0; i < Fideos.length; i++) {
        if (Fideos[i] == Fideos[verF]) {
            Escri = Escri + "<tr><td>" + [i] + "</td><td>" + Fideos[i] + "</td><td>" + Cantidad[i] + "</td><td>" + Precio[i] + "</td><td>" + Marca[i] + "</td></tr>";
        }
    }
    Escri = "<table border=true><tr><th>N°</th><th>Fideos</th><th>Cantidad</th><th>Precio</th><th>Marca</th></tr>" + Escri + "</table>";
    document.getElementById("VER").innerHTML = Escri;
}

function Ordenado() {
    do {
        var bandera2 = true;
        for (var i = 0; i < Fideos.length; i++) {
            if (Cantidad[i] < Cantidad[i + 1]) {

                var auxiliar = Cantidad[i];
                Cantidad[i] = Cantidad[i + 1];
                Cantidad[i + 1] = auxiliar;

                var auxiliar3 = Fideos[i];
                Fideos[i] = Fideos[i + 1];
                Fideos[i + 1] = auxiliar3;

                var auxiliare1 = Precio[i];
                Precio[i] = Precio[i + 1];
                Precio[i + 1] = auxiliare1;

                var auxiliare2 = Marca[i];
                Marca[i] = Marca[i + 1];
                Marca[i + 1] = auxiliare2;

                bandera2 = false;
            }
        }
    } while (bandera2 == false);
    Listado();
}

function ElimTodo() {
    do {
        var bandera = true;
        for (var i = 0; i < Fideos.length; i++) {
            if (Fideos[i] != "") {
                Fideos.splice(i, 1);
                Cantidad.splice(i, 1);
                Precio.splice(i, 1);
                Marca.splice(i, 1);
                bandera = false;
            }
        }
    } while (bandera == false)
    Listado();
}

function SubMenu() {
    var seleccionada = document.getElementsByName("Eliminacion");

    for (var i = 0; i < seleccionada.length; i++) {
        console.log(seleccionada);
    }

    if (seleccionada[0].checked) {
        do {
            var bandera = true;
            for (var i = 0; i < Fideos.length; i++) {
                if (Precio[i] >= 100) {
                    Fideos.splice(i, 1);
                    Cantidad.splice(i, 1);
                    Precio.splice(i, 1);
                    Marca.splice(i, 1);
                    bandera = false;
                }
            }
        } while (bandera == false)
        Listado();
    }

    if (seleccionada[1].checked) {
        do {
            var bandera = true;
            for (var i = 0; i < Fideos.length; i++) {
                if (Precio[i] <= 100) {
                    Fideos.splice(i, 1);
                    Cantidad.splice(i, 1);
                    Precio.splice(i, 1);
                    Marca.splice(i, 1);
                    bandera = false;
                }
            }
        } while (bandera != true)
        Listado();
    }
}

function Porcentaje() {
    var Eleccion = document.getElementsByName("Incremento");
    
    for(var i = 0; i < Eleccion.length; i++){
        console.log(Eleccion[i].value + " " + Eleccion[i].checked)
    }
    if (Eleccion[0].checked) {
        console.log("Estoy en 5%");
        for (var i = 0; i < Precio.length; i++) {
            Precio[i] = Math.round(Precio[i] * 1.05);
        }
        Listado();
    }

    if (Eleccion[1].checked) {
        console.log("Estoy en 10%");
        for (var j = 0; j < Precio.length; j++) {
            Precio[j] = Math.round(Precio[j] * 1.1);
        }
        Listado();
    }

    if (Eleccion[2].checked) {
        console.log("Estoy en 15%");
        for (var l = 0; l < Precio.length; l++) {
            Precio[l] = Math.round(Precio[l] * 1.15);
        }
        Listado();
    }
}

function Modificacion1() {
    var produc = document.getElementById("Mod1").value;
    var Remplazo = document.getElementById("Rem").value;

    for (var i = 0; i < Fideos.length; i++) {
        if (Fideos[i] == Fideos[produc]) {
            Fideos[produc] = Remplazo;
        }
    }

    Listado();
}

function NewRegistro() {
    var Posicion = document.getElementById("POS").value;
    var NewCan = document.getElementById("NewC").value;
    var NewPre = document.getElementById("NewP").value;
    var NewMar = document.getElementById("NewM").value;

    Cantidad[Posicion] = NewCan;
    Precio[Posicion] = NewPre;
    Marca[Posicion] = NewMar;

    Listado();
} 