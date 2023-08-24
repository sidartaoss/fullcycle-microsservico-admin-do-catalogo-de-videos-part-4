package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.ActivationStatus;
import com.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoryTest {

    @Nested
    class NewCategory {

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertNotNull(actualCategory);
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_id() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertNotNull(actualCategory.getId());

        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_name() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertEquals(expectedName, actualCategory.getName());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_description() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertEquals(expectedDescription, actualCategory.getDescription());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_with_activation_status_as_active() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = ActivationStatus.ACTIVE;
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertEquals(expectedIsActive, actualCategory.getActivationStatus());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_createdAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertNotNull(actualCategory.getCreatedAt());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_updatedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertNotNull(actualCategory.getUpdatedAt());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_Then_should_instantiate_a_category_with_deletedAt_as_null() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            // when
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            // then
            assertNull(actualCategory.getDeletedAt());
        }
    }

    @Nested
    class Validate {

        @Test
        void Given_an_invalid_null_name_When_call_newCategory_and_validate_Then_should_receive_an_error() {
            // given
            final String expectedNameAsNull = null;
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorMessage = "'name' should not be null";
            // when
            final var notification = Category.newCategory(expectedNameAsNull, expectedDescription)
                    .getNotification();
            // then
            assertEquals(expectedErrorMessage, notification.firstError().message());
        }

        @Test
        void Given_an_invalid_null_name_When_call_newCategory_and_validate_Then_should_count_an_error() {
            // given
            final String expectedNameAsNull = null;
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorCount = 1;
            // when
            final var notification = Category.newCategory(expectedNameAsNull, expectedDescription)
                    .getNotification();
            // then
            assertEquals(expectedErrorCount, notification.getErrors().size());
        }

        @Test
        void Given_an_invalid_empty_name_When_call_newCategory_and_validate_Then_should_receive_an_error() {
            // given
            final String expectedName = " ";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorMessage = "'name' should not be empty";
            // when
            final var notification = Category.newCategory(expectedName, expectedDescription).getNotification();
            // then
            assertEquals(expectedErrorMessage, notification.firstError().message());
        }

        @Test
        void Given_an_invalid_empty_name_When_call_newCategory_and_validate_Then_should_count_an_error() {
            // given
            final String expectedName = " ";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorCount = 1;
            // when
            final var notification = Category.newCategory(expectedName, expectedDescription).getNotification();
            // then
            assertEquals(expectedErrorCount, notification.getErrors().size());
        }

        @Test
        void Given_an_invalid_name_length_less_than_3_When_call_newCategory_and_validate_Then_should_receive_error() {
            // given
            final String expectedName = "Fi ";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
            // when
            final var notification = Category.newCategory(expectedName, expectedDescription).getNotification();
            // then
            assertEquals(expectedErrorMessage, notification.firstError().message());
        }

        @Test
        void Given_an_invalid_name_length_less_than_3_When_call_newCategory_and_validate_Then_should_count_an_error() {
            // given
            final String expectedName = "Fi ";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedErrorCount = 1;
            // when
            final var notification = Category.newCategory(expectedName, expectedDescription).getNotification();
            // then
            assertEquals(expectedErrorCount, notification.getErrors().size());
        }

        @Test
        void Given_a_valid_empty_description_When_call_newCategory_and_validate_then_should_not_throw_error() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = " ";
            final var actualCategory = Category.newCategory(expectedName, expectedDescription);
            final var notification = Notification.create();
            // when
            Executable validMethodCall = () -> actualCategory.validate(notification);
            // then
            assertDoesNotThrow(validMethodCall);
        }
    }

    @Nested
    class NewCategoryAndDeactivate {

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertNotNull(actualCategory);
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_with_id() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertNotNull(actualCategory.getId());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_with_name() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedName, actualCategory.getName());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_with_a_description() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedDescription, actualCategory.getDescription());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_as_inactive() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = ActivationStatus.INACTIVE;
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedIsActive, actualCategory.getActivationStatus());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_with_createdAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            final var createdAt = aCategory.getCreatedAt();
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(createdAt, actualCategory.getCreatedAt());
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_with_updatedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            final var updatedAt = aCategory.getUpdatedAt().truncatedTo(ChronoUnit.SECONDS);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(updatedAt, actualCategory.getUpdatedAt().truncatedTo(ChronoUnit.SECONDS));
        }

        @Test
        void Given_a_valid_param_When_call_newCategory_and_deactivate_Then_should_create_a_category_with_deletedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            final var deletedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(deletedAt, actualCategory.getDeletedAt().truncatedTo(ChronoUnit.SECONDS));
        }
    }

    @Nested
    class Deactivate {

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_inactivated_with_the_same_id() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(aCategory.getId(), actualCategory.getId());
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_inactivated_with_the_same_name() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedName, actualCategory.getName());
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_inactivated_and_same_description() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedDescription, actualCategory.getDescription());
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_activation_status_as_inactive() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = ActivationStatus.INACTIVE;
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(expectedIsActive, actualCategory.getActivationStatus());
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_inactivated_and_same_createdAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            final Instant createdAt = aCategory.getCreatedAt();
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertEquals(createdAt, actualCategory.getCreatedAt());
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_a_new_value_for_updatedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            final Instant updatedAt = aCategory.getUpdatedAt();
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_a_new_value_for_deletedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription);
            // when
            final Category actualCategory = aCategory.deactivate();
            // then
            assertNotNull(actualCategory.getDeletedAt());
        }
    }

    @Nested
    class Activate {

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_activated_and_the_same_id() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertEquals(aCategory.getId(), actualCategory.getId());
        }

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_activated_and_the_same_name() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription)
                    .deactivate();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertEquals(expectedName, actualCategory.getName());
        }

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_activated_and_the_same_description() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertEquals(expectedDescription, actualCategory.getDescription());
        }

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_activation_status_as_active() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var expectedIsActive = ActivationStatus.ACTIVE;
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertEquals(expectedIsActive, actualCategory.getActivationStatus());
        }

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_activated_and_the_same_createdAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            final Instant createdAt = aCategory.getCreatedAt();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertEquals(createdAt, actualCategory.getCreatedAt());
        }

        @Test
        void Given_a_valid_inactive_category_When_call_activate_Then_should_return_new_value_for_updatedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            final Instant updatedAt = aCategory.getUpdatedAt();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        }

        @Test
        void Given_a_valid_active_category_When_call_deactivate_Then_should_return_a_null_value_for_deletedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory(expectedName, expectedDescription).deactivate();
            // when
            final Category actualCategory = aCategory.activate();
            // then
            assertNull(actualCategory.getDeletedAt());
        }
    }

    @Nested
    class Update {

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_category_updated_and_the_same_id() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            final var id = aCategory.getId();
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertEquals(id, actualCategory.getId());
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_category_and_a_new_value_for_name() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertEquals(expectedName, actualCategory.getName());
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_category_and_a_new_value_for_description() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertEquals(expectedDescription, actualCategory.getDescription());
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_the_same_value_for_activation_status() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            final var isActive = aCategory.getActivationStatus();
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertEquals(isActive, actualCategory.getActivationStatus());
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_category_updated_and_the_same_createdAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            final Instant createdAt = aCategory.getCreatedAt();
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertEquals(createdAt, actualCategory.getCreatedAt());
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_updated_and_a_new_value_for_updatedAt() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            final Instant updatedAt = aCategory.getUpdatedAt();
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertTrue(updatedAt.isBefore(actualCategory.getUpdatedAt()));
        }

        @Test
        void Given_a_valid_category_When_call_update_Then_should_return_updated_and_deletedAt_as_null() {
            // given
            final var expectedName = "Filmes";
            final var expectedDescription = "A categoria mais assistida";
            final var aCategory = Category.newCategory("Film", "A categoria");
            // when
            final Category actualCategory = aCategory.update(expectedName, expectedDescription);
            // then
            assertNull(actualCategory.getDeletedAt());
        }
    }
}