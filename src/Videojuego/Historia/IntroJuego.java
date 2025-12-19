package Videojuego.Historia;

public class IntroJuego {

    public static String intro;

    public static void Introduccion(String args) {
        intro = "Hace diez milenios, nació una profecía.\n\n" +
                        "En ella se anunciaba que el día en que el Gran Dragón, \"Azharyon\", el Aliento Eterno,\n" +
                        "fuera besado por la muerte, el mundo entero sería consumido por su odio y su rencor.\n" +
                        "Muchos lo tomaron como un cuento o un mito… pues Azharyon era inmortal.\n" +
                        "Durante eras incontables, reinó sobre los cielos, y la vida floreció bajo su sombra ardiente.\n\n" +
                        "Pero nada es eterno.\n\n" +
                        "Ahora, la profecía se ha cumplido. La caída de Azharyon ha dado a luz a la Nebulosa,\n" +
                        "el oscuro Miasma de Umbra, una neblina de color morado oscuro que se arrastra sobre montañas y cavernas,\n" +
                        "un veneno que se apodera del alma de todos los seres, destruyendo su voluntad y convirtiéndolos en bestias,\n" +
                        "corrompiendo todo lo que toca. Monstruos, hombres, incluso los espíritus… todos se retuercen bajo su embrujo.\n\n" +
                        "En el corazón del Reino de Haeral, joya brillante de los mortales, se alza el Palacio de Cristal.\n" +
                        "Allí, el Rey Lorian convoca a tres jóvenes guerreros: un espadachín valiente, un arquero certero\n" +
                        "y un mago de antigua sabiduría.\n\n" +
                        "Ellos deberán descender al caos, enfrentar a las criaturas deformadas por la Nebulosa,\n" +
                        "y abrir camino hacia las faldas de las Montañas Prohibidas.\n\n" +
                        "Muchos antes lo intentaron. Ninguno regresó.\n\n" +
                        "Ahora, el destino del mundo se decide.\n" +
                        "Y su viaje… está a punto de comenzar.\n";

        mostrarTextoLento(intro, 40); // velocidad: 40ms por caracter
    }

    public static void mostrarTextoLento(String texto, int delayMs) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                // Pequeña pausa después de cada caracter
                Thread.sleep(delayMs);

                // Pausa más larga al final de frases
                if (c == '.' || c == '…') {
                    Thread.sleep(200);
                }

                // Pausa aún más larga en saltos de línea dobles (párrafos)
                if (c == '\n') {
                    Thread.sleep(150);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
