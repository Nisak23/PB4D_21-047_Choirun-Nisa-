package com.example.modul22

object galaxy_data {
    private val judul = arrayOf(
        "Galaksi Bima Sakti",
        "Galaksi Andromeda (M31)",
        "Galaksi Magellan Besar",
        "Galaksi Sombrero",
        "Galaksi The Rose (Arp 273)",
        "Galaksi Sculptor (NGC 253)",
        "Galaksi Black Eye (M64)",
        "Galaksi Roda Biru (Blue Pinwheel Galaxy)",
        "Galaksi Cartwheel",
        "Galaksi Bunga Matahari (Sunflower)",
        "Galaksi Ursa Mayor",
        "Galaksi Pusaran Air (M51)"
    )
    private val deskripsi = arrayOf(
        "Galaksi yang berbentuk spiral ini adalah tempat kita hidup bersama manusia lain di planet bumi",
        "Galaksi lain yang sama kerap dikenal oleh kalangan umum adalah Galaksi Andromeda, sebab dapat dilihat secara langsung tanpa teropong",
        "Berada di belahan langit selatan, kira-kira berjarak 150.000 tahun cahaya dari galaksi Bima Sakti",
        "Bentuk galaksi ini adalah spiral dan terlihat seperti topi sombrero",
        "Bentuknya seperti bunga mawar dan berada di rasi bintang Andromeda",
        "Memiliki banyak debu, bahkan selur-selur debunya kerap terlihat naik dari piringan galaksinya",
        "Memiliki pita gelap yang spektakuler dalam menyerap debu yang ada di depan inti terang galaksi",
        "Memiliki bentuk bundar layaknya roda dengan warna biru",
        "Memiliki ukuran jarak sekitar 150.000 tahun cahaya",
        "Tampilannya hampir menyerupai bunga matahari",
        "Nama lain dari galaksi ini adalah Galaksi Bintang Biduk",
        "Bentuk tampilannya seolah menyerupai pusaran air, yang mana terjadi akibat gangguan gravitasi di alam semesta"
    )
    private val photo = intArrayOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j,
        R.drawable.k,
        R.drawable.l,
    )
   val listdata: ArrayList<galaxy>
           get(){
               var list = arrayListOf<galaxy>()
               for(position in judul.indices){
                   val galaxy = galaxy()
                   galaxy.judul= judul[position]
                   galaxy.deskripsi= deskripsi[position]
                   galaxy.photo= photo[position]
                   list.add(galaxy)
               }
               return list
           }
}