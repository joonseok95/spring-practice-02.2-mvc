package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 20000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 20000, 10);
        Item item2 = new Item("itemB", 15000, 5);
        //when
        Item save1 = itemRepository.save(item1);
        Item save2 = itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();
        //then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(save1, save2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("itemA", 20000, 10);

        Item save1 = itemRepository.save(item1);
        Long id = save1.getId();

        //when
        Item updateOnly = new Item("itemB", 10000, 50);
        itemRepository.update(id, updateOnly);

        //then
        Item byId = itemRepository.findById(id);
        Assertions.assertThat(byId.getItemName()).isEqualTo(updateOnly.getItemName());
        Assertions.assertThat(byId.getPrice()).isEqualTo(updateOnly.getPrice());
        Assertions.assertThat(byId.getQuantity()).isEqualTo(updateOnly.getQuantity());

    }

}