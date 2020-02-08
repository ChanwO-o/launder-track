# Purpose of this project is to understand how the MVVM architecture is used in an Android app.
(and to  track my laundry)

I model two classes:
- Wardrobe
	- A structure that holds a list of ClothItem objects
- ClothItem
	- A structure that details one cloth item (e.g. shirt) with name and color attributes, and a list of dates that mark when the cloth was washed.

![Android MVVM diagram]([https://developer.android.com/topic/libraries/architecture/images/final-architecture.png](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png))

The data of this app revolves around a list of Wardrobe objects (which hold lists of cloth items). Each layer of data is maintained and displayed in a RecyclerView with read/write/insert/delete/edit operations. Of course, with proper implementations of MVVM.

The **Repository** is responsible of reading and writing the data, and passing it on to the **ViewModels**. The Repository packages the raw data (Arraylists) into **LiveData** objects that allow the **Views** to observe and update themselves as data is changed.