package com.Audisankara.asit.objects

import com.Audisankara.asit.Models.SearchModel
import com.Audisankara.asit.R

object SearchRecyclerDATA {

     fun academicsRecycler() : ArrayList<SearchModel> {
        val data = ArrayList<SearchModel>()
         data.add(SearchModel("Assignments","Writing assignments is now\neasier than before","Academics",
             R.color.CustomPurple))
        data.add(SearchModel("Syllabus","Get the syllabus of Present Semester\nof Btech 3rd year .","Academics",
            R.color.CustomPurple))
        data.add(SearchModel("Previous Papers","Get the Model papers\nof last semester .","Academics",
            R.color.CustomPurple))
        data.add(SearchModel("Materials","Skipped so many classes?\nno worry's we got you","Academics",
            R.color.CustomPurple))
        return data
    }
}