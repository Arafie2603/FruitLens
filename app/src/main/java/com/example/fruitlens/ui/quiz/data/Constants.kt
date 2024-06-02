package com.example.fruitlens.ui.quiz.data

import com.example.fruitlens.R

object Constants {
    fun getQuestion() : ArrayList<Question> {
        val questionsList =ArrayList<Question>()
        val que1 = Question(1,
            "Buah apa yang ada di gambar ?",
            R.drawable.alpukat,
            "Alpukat",
            "Duku",
            "Pisang",
            1)

        val que2 = Question(2,
            "Buah apa yang ada di gambar ?", R.drawable.apel,
            "Kurma",
            "Kelapa",
            "Apel",
            3)


        val que3 = Question(3,
            "Buah apa yang ada di gambar ?", R.drawable.durian,
            "Leci",
            "Durian",
            "Cermai",
            2)


        val que4 = Question(4,
            "Buah apa yang ada di gambar ?", R.drawable.jeruk,
            "Sirsak",
            "Manggis",
            "Jeruk",
            3)


        val que5 = Question(5,
            "Buah apa yang ada di gambar ?", R.drawable.kelapa,
            "Kelapa",
            "Durian",
            "Jambu",
            1)


        val que6 = Question(6,
            "Buah apa yang ada di gambar ?", R.drawable.kelengkeng,
            "Kiwi",
            "Sawo",
            "Kelengekng",
            3)


        val que7 = Question(7,
            "Buah apa yang ada di gambar ?", R.drawable.kiwi,
            "Belimbing",
            "Kiwi",
            "Cermai",
            2)


        val que8 = Question(8,
            "Buah apa yang ada di gambar ?", R.drawable.mangga,
            "Mangga",
            "Jeruk",
            "Kelengkeng",
            1)


        val que9 = Question(9,
            "Buah apa yang ada di gambar ?", R.drawable.manggis,
            "Alpukat",
            "Kedongdong",
            "Manggis",
            3)


        val que10 = Question(10,
            "Buah apa yang ada di gambar ?", R.drawable.melon,
            "Melon",
            "Semangka",
            "Markisa",
            1)
        val que11 = Question(11,
            "Buah apa yang ada di gambar ?", R.drawable.naga,
            "Jambu",
            "Naga",
            "Jeruk",
            2)
        val que12 = Question(12,
            "Buah apa yang ada di gambar ?", R.drawable.nanas,
            "Pepaya",
            "Nanas",
            "Sawo",
            2)
        val que13 = Question(13,
            "Buah apa yang ada di gambar ?", R.drawable.pir,
            "Jambu",
            "Anggur",
            "Pir",
            3)
        val que14 = Question(14,
            "Buah apa yang ada di gambar ?", R.drawable.salak,
            "Salak",
            "Duku",
            "Nanas",
            1)
        val que15 = Question(15,
            "Buah apa yang ada di gambar ?", R.drawable.semangka_buah,
            "Durian",
            "Melon",
            "Semangka",
            3)

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)
        questionsList.add(que11)
        questionsList.add(que12)
        questionsList.add(que13)
        questionsList.add(que14)
        questionsList.add(que15)

        return questionsList
    }
}