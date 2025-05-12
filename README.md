# AyED2TablaHash
Implementacion de una Tabla Hash con Tareas
Clase Tarea
Es la clase que representa cada tarea. El atributo más importante es el código, que lo genero automáticamente con un UUID, como pedía el enunciado.

Además, la tarea tiene:

un nombre y una descripción,

un estado que puede ser 0 (pendiente), 1 (en progreso) o 2 (finalizada),

una fechaInicio que se genera al momento de crear la tarea,

y un booleano esAlta que me sirve para hacer bajas lógicas.

También un metodo toString() para mostrar bien la informacion de cada tarea cuando la imprimo.

Clase TablaDispersa
Acá se implementa la tabla hash. Es un array de Tarea de tamaño 101.

Para insertar, uso el método de la multiplicación con una constante A = 0.6180339887. El valor numérico lo obtengo del UUID, haciendo XOR entre los bits más y menos significativos.

Si la posición ya está ocupada, uso exploración cuadrática, sumando i^2 a la posición base hasta encontrar un espacio libre.

El método insertar devuelve false si no hay espacio.

En buscar y eliminar, aplico la misma lógica de colisión: arranco desde la posición base y recorro con cuadrática hasta encontrar la tarea (o hasta que llegue a una celda vacía).

Para eliminar, simplemente marco la tarea como baja (esAlta = false).

También hice una función para calcular el factor de carga, que divide la cantidad de elementos por el tamaño total de la tabla.

Y agregué un mostrarTabla() para poder ver todas las tareas activas en la tabla.

🟦 Clase Main
Es un menú por consola que permite:

Ingresar tareas

Buscar una tarea por código

Eliminar una tarea

Mostrar la tabla

Agregué validaciones para que el usuario no meta estados inválidos ni más de 101 tareas. También uso try-catch para manejar errores de tipo de entrada.

Cada vez que el usuario hace algo, trato de "limpiar" la consola con una secuencia ANSI, aunque sé que en IntelliJ no funciona (funciona si se corre desde la terminal).

