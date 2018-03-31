package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.FoodCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FoodDbSchema.FoodTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FoodDbSchema.FoodTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;

/**
 * Created by dimantik on 3/5/18.
 */

public class FoodLab {

    private static FoodLab sFoodLab;

    private SQLiteDatabase mFoodDatabase;

    public static FoodLab get(SQLiteDatabase database){
        if (sFoodLab == null){
            sFoodLab = new FoodLab(database);
        }
        return sFoodLab;
    }

    private FoodLab(SQLiteDatabase database){
        mFoodDatabase = database;
    }

    public void addFood(Food food){
        ContentValues contentValues = getContentValues(food);

        mFoodDatabase.insert(FoodTable.TABLE_NAME, null, contentValues);
    }

    public void updateFood(Food food){
        String uuidString = food.getId().toString();
        ContentValues contentValues = getContentValues(food);

        mFoodDatabase.update(FoodTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteFood(Food food){
        mFoodDatabase.delete(FoodTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{food.getId().toString()});
    }

    public void deleteAllFoods(){
        mFoodDatabase.delete(FoodTable.TABLE_NAME, null, null);
    }

    public void addFoods(List<Food> foods){
        for (Food food : foods){
            addFood(food);
        }
    }

    public Map<UUID, Food> getFoods(){
        Map<UUID, Food> foods = new HashMap<>();

        FoodCursorWrapper cursor = queryFoods(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Food food = cursor.getFood();
                foods.put(food.getId(), food);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return foods;
    }

    public Food getFood(UUID id){
        FoodCursorWrapper cursor = queryFoods(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getFood();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Food food){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, food.getId().toString());
        contentValues.put(Cols.NAME, food.getName());
        contentValues.put(Cols.IMAGE_NAME, food.getAssertDrawable());
        contentValues.put(Cols.QUANTITY, food.getQuantity());

        return contentValues;
    }

    private FoodCursorWrapper queryFoods(String whereClause, String[] whereArgs){
        Cursor cursor = mFoodDatabase.query(
                FoodTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new FoodCursorWrapper(cursor);
    }
}
