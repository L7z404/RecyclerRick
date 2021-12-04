package castillo.castaneda.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import castillo.castaneda.rickandmorty.R
import castillo.castaneda.rickandmorty.model.Personaje
import com.squareup.picasso.Picasso

class PersonajesAdapter(ctx:Context,listaPersonajes:List<Personaje>): RecyclerView.Adapter<PersonajesAdapter.PersonajesVH>() {
    lateinit var ctx:Context
    lateinit var personajesList:List<Personaje>

    init {
        this.ctx = ctx
        this.personajesList = listaPersonajes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesVH {
        val vistaPersonaje = LayoutInflater.from(ctx).inflate(R.layout.card_personaje,parent,false)
        val viewHolderPersonaje = PersonajesVH(vistaPersonaje)
        vistaPersonaje.tag=viewHolderPersonaje
        return viewHolderPersonaje
    }

    override fun onBindViewHolder(holder: PersonajesVH, position: Int) {
        holder.tvname.text = personajesList[position].nombre
        holder.tvlocation.text = personajesList[position].UltimaLocacion
        Picasso.get()
            .load(personajesList[position].imagen)
            .into(holder.ivfoto)
    }

    override fun getItemCount(): Int {
        return this.personajesList!!.size
    }

    class PersonajesVH(vistaIndividual: View):RecyclerView.ViewHolder(vistaIndividual){
        lateinit var ivfoto:ImageView
        lateinit var tvname:TextView
        lateinit var tvlocation:TextView

        init {
            ivfoto=vistaIndividual.findViewById(R.id.ivimage)
            tvname=vistaIndividual.findViewById(R.id.tvname)
            tvlocation=vistaIndividual.findViewById(R.id.textView3)
        }

    }
}