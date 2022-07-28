package model;

import api.Requeteur;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InterventionAdapter {

    public ArrayList<Intervention> listInterventions() {
        Requeteur requeteur = new Requeteur();
        String json = requeteur.GetRequeteur("/users");
        JSONObject jsonObject = new JSONObject(json);

        JSONArray jsonArray = jsonObject.getJSONArray("users");
        ArrayList<Intervention> interventions = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);

            int id = data.getInt("id");
            String name = data.getString("firstName") + " " + data.getString("lastName");
            String email = data.getString("email");
           // System.out.println(data);
            Object addressData = data.get("address");
            JSONObject addressI = new JSONObject(addressData.toString());

            Object coordinates = addressI.get("coordinates");

            JSONObject coordinatesI = new JSONObject(coordinates.toString());
            double longitude = coordinatesI.getDouble("lng");
            double latitude = coordinatesI.getDouble("lat");
            String phone = data.getString("phone");

            String address = addressI.getString("address") + " " + addressI.getString("city") + " " + addressI.getString("postalCode");

            String image = data.getString("image");
            Intervention intervention = new Intervention(id, name, email, address, phone, image, longitude, latitude);
            interventions.add(intervention);


        }
        //System.out.println(interventions.size() + " taille");
        return interventions;
    }
}
