package service;

import model.Pet;
import model.Tutor;
import repository.PetRepository;
import repository.TutorRepository;

import java.util.List;

public class TutorService {

    private TutorRepository tutorRepo;
    private PetRepository petRepo;

    public TutorService(TutorRepository tutorRepo, PetRepository petRepo) {
        this.tutorRepo = tutorRepo;
        this.petRepo = petRepo;
    }

    public void listarTutoresComPets() {
        List<Tutor> tutores = tutorRepo.listarTutores();

        for (Tutor t : tutores) {
            System.out.println("Tutor ID: " + t.getId());
            System.out.println("Nome: " + t.getNome());
            System.out.println("Idade: " + t.getIdade());
            System.out.println("Telefone: " + t.getTelefone());
            System.out.println("Gmail: " + t.getGmail());
            System.out.println("Pets:");

            boolean temPets = false;

            for (Pet p : petRepo.getTodosAnimais()) {
                if (p.getTutor() != null && p.getTutor().getId() == t.getId()) {
                    System.out.println("  Pet ID: " + p.getId());
                    System.out.println("  Nome: " + p.getNomePet());
                    System.out.println("  Raça: " + p.getRacaPet());
                    System.out.println("  Idade: " + p.getIdadePet());
                    System.out.println("===================================================");
                    temPets = true;
                }
            }

            if (!temPets) {
                System.out.println("  Nenhum pet cadastrado para este tutor.");
            }

            System.out.println();
        }
    }
}
