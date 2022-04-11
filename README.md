# embidicatest
Car Directory

get   /  возвращает список всех машин
  params:
  
    filter(not required): 
    
      reg - Сортировка по регистрационному знаку ASC
      model - Сортировка по модели машины ASC
      color - Сортировка по цвету модели ASC
      yearasc - Сортировка по году выпуска ASC
      yeardesc - Сортировка по году выпуска DESC 
      
    dateTo(not required): int год выпуска, до которого нужно получить список авто  
    dateFrom(not required): int год выпуска, после которого нужно получить список авто
    
post  /add  добавить новое авто
  RequestBody json :
  
    {
      "registerSign": String,
      "carModel": String,
      "color": String,
      "yearOfIssue" " int
    }
    
delete    /delete  удалить авто по id
    params:
    
        id - id авто
        
        
 Spring приложение на java 17
 DB postgresql
 BuildTool Gradle
 
 Запросы кэшируется, при добвлении и удалении авто кэш обновляется
