package castillo.castaneda.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import castillo.castaneda.rickandmorty.adapter.PersonajesAdapter
import castillo.castaneda.rickandmorty.model.Personaje
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var rvPersonajes:RecyclerView
    var listaPersonajes:MutableList<Personaje> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPersonajes = findViewById(R.id.recyclerpersonajes)

        rvPersonajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        var adapterdor = PersonajesAdapter(this, listaPersonajes)
        rvPersonajes.adapter = adapterdor
        //https://rickandmortyapi.com/api/character

        val pila = Volley.newRequestQueue(this)
        val url = "https://rickandmortyapi.com/api/character"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener() { response ->
//            Log.d("Respuesta",response.toString())
            val respuesta = response.getJSONArray("results")
            val tamRespuesta = respuesta.length()
            Log.d("respuesta", "el tamaño es ${respuesta.length()}")
            for (dato in 0 until tamRespuesta){
                val personajeJson = respuesta.getJSONObject(dato)
                val personaje = Personaje(
                    personajeJson.getString("name"),
                    personajeJson.getJSONObject("location").getString("name"),
                    personajeJson.getString("image"))
                listaPersonajes.add(personaje)
                Log.d("respuesta", "${personaje.nombre}")
            }
            adapterdor.notifyDataSetChanged()
        },
            {
                Log.d("Respuesta","No charcha")
            })

        pila.add(jsonObjectRequest)
    }
}