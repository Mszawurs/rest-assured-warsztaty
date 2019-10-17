package io.swagger.pet.store.tests.framework.tests;

import io.swagger.pet.store.tests.chapter4.TestUtils;
import io.swagger.pet.store.tests.framework.methods.pet.GetPetApiMethod;
import io.swagger.pet.store.tests.framework.methods.pet.PostPetApiMethod;
import io.swagger.petstore.model.Category;
import io.swagger.petstore.model.Pet;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.swagger.petstore.model.Pet.StatusEnum.AVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {

    @Test
    public void getPetTest() {
        GetPetApiMethod getPetApiMethod = new GetPetApiMethod();
        getPetApiMethod.setPetId("123");
        getPetApiMethod.sendRequest();
        getPetApiMethod.assertRequestSuccess();
        getPetApiMethod.assertStatusCode(200);
        Pet pet = getPetApiMethod.getResponseModel();

        assertThat(pet.getId()).isEqualTo(123L);
    }

    @Test
    public void postPetTest() {
        Pet petToBeAdded = getTestPet();

        PostPetApiMethod postPetApiMethod = new PostPetApiMethod();
        postPetApiMethod.setPet(petToBeAdded);
        postPetApiMethod.sendRequest();
        postPetApiMethod.assertRequestSuccess();
        Pet createdPet = postPetApiMethod.getResponseModel();

        assertThat(createdPet.getId()).isEqualTo(petToBeAdded.getId());
    }

    private Pet getTestPet() {
        return new Pet().id(TestUtils.nextId()).name("alex").status(AVAILABLE)
                .category(new Category().id(TestUtils.nextId()).name("dog"))
                .photoUrls(Arrays.asList("http://foo.bar.com/1"));
    }

}