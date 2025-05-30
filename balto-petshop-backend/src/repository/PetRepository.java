package repository;

import model.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    private List<Pet> animais = new ArrayList<>();
    private int proximoId = 1;

    public Pet buscarPetPorId(int idPet) {
        for (Pet pet : animais) {
            if (pet.getId() == idPet) {
                return pet;
            }
        }
        return null;
    }

    public List<Pet> getTodosAnimais() {
        return new ArrayList<>(animais);
    }

    public void adicionarPet(Pet pet) {
        pet.setId(proximoId);
        animais.add(pet);
        proximoId++;
    }

    public Pet buscarPorId(int id) {
        for (Pet p : animais) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizarPet(Pet petAtualizado) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == petAtualizado.getId()) {
                animais.set(i, petAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerPet(int id) {
        return animais.removeIf(p -> p.getId() == id);
    }
}
