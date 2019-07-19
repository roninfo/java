package default_methods;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;

public class DefaultMethods {

    public static void main(String arg[]) {

        Usuario user1 = new Usuario("Roni Palacio", 150);
        Usuario user2 = new Usuario("Larissa Palacio", 120);
        Usuario user3 = new Usuario("Marcia Ramos", 180);

        var usuarios = List.of(user1, user2, user3);

        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
        Consumer<Usuario> imprimeNomes = u -> System.out.println(u.name);

        usuarios.forEach(mostraMensagem.andThen(imprimeNomes));

        usuarios.removeIf(u -> u.getPontos() < 110);

    }



    static class Usuario {
        private String name;
        private int pontos;

        public Usuario(String name, int pontos) {
            this.name = name;
            this.pontos = pontos;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPontos() {
            return pontos;
        }

        public void setPontos(int pontos) {
            this.pontos = pontos;
        }
    }
}
