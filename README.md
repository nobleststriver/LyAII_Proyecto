# Proyecto Compilador - Lenguajes y Autómatas II

Este repositorio contiene el desarrollo del **Proyecto Compilador** correspondiente a la materia de **Lenguajes y Autómatas II**, impartida por el profesor **Víctor Manuel Bátiz Beltrán** en el **Instituto Tecnológico de Culiacán**.

El proyecto parte del código base proporcionado por el profesor en el repositorio [vbatiz/LyAII_CodigoBase](https://github.com/vbatiz/LyAII_CodigoBase), y es actualizado periódicamente conforme se avanza en clase.

## Avance 1

En el primer avance, el equipo implementó las siguientes funcionalidades:

- Lectura de un archivo de código fuente desde el sistema de archivos local, permitiendo al usuario seleccionar el archivo de su preferencia.
- Conservación de la gramática base especificada por el profesor.
- Generación de un documento de entrega con capturas de pantalla mostrando el funcionamiento del programa.

<br>

**Gramática inicial**:<br>
Program -> D S &lt;eof&gt;<br>
D → id (int | float) ; D<br>
D → ε<br>
S → if E then S else S<br>
S → begin S L<br>
S → id := E<br>
S → print E<br>
L → end<br>
L → ; S L<br>
E → id == id<br>
E → id + id<br>

## Avance 2

Para el segundo avance, se extendió la funcionalidad del compilador con lo siguiente:

- Soporte para declaraciones de variables tipo `double` (compatibles con `float`).
- Soporte para declaraciones de variables tipo `long` (compatibles con `int`).
- Soporte para operaciones de **resta (-)**, **multiplicación (\*)** y **división (/)**.
- Documento de entrega con evidencias gráficas y ejemplos funcionales.

<br>

**Gramática objetivo**:<br>
Program -> D S &lt;eof&gt;<br>
D → id (int | `long` | float | `double`) ; D<br>
D → ε<br>
S → if E then S else S<br>
S → begin S L<br>
S → id := E<br>
S → print E<br>
L → end<br>
L → ; S L<br>
E → id == id<br>
E → id + id<br>
`E → id - id` <br>
`E → id * id`<br>
`E → id / id`<br>

## Integrantes del equipo

- **Angulo Avilés Atanacio Alejandro**
- **Clemente López Johana**
- **Cruz Cebreros José Javier**
- **Jáuregui Uriarte Manuel**
- **Noriega Nevarez Jared Emmanuel**

## Licencia

Este proyecto es de carácter académico. Todos los derechos pertenecen a sus autores. El código base original es propiedad del profesor [vbatiz](https://github.com/vbatiz).
