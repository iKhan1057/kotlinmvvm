package com.kotlinmvvm.viewmodel

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinmvvm.data.CelebsModel
import com.kotlinmvvm.data.CelebsServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.io.InputStream


class CelebsViewModel : ViewModel() {
    val celebsdetails = MutableLiveData<List<CelebsModel>>()
    val loading = MutableLiveData<Boolean>()
    val celebsLoadError = MutableLiveData<Boolean>()
    fun refresh() {
        getCelebsDetails()
    }

    private val celebsService = CelebsServices()
    private val disposable = CompositeDisposable()

    private fun getCelebsDetails() {
        celebsLoadError.value = false
        loading.value = true
        disposable.add(
            celebsService.getCelebs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CelebsModel>>() {
                    override fun onSuccess(value: List<CelebsModel>?) {
                        celebsdetails.value = value
                        celebsLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        celebsLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

//from assets
//fun refresh(context: Context) {
//    getCelebsDetails(context)
//}

//    private fun getCelebsDetails(context: Context) {
//        val celebslistdata: MutableList<CelebsModel> = ArrayList()
//        val parseddata = assetJSONFile("celebs.json", context)
//        val objectparse = JSONObject(parseddata)
//        objectparse.let {
//            val arraycelebs = objectparse.optJSONArray("celebs")
//            arraycelebs?.let {
//                for (i in 0 until arraycelebs.length()) {
//                    val celebsobject: JSONObject = arraycelebs[i] as JSONObject
//                    celebslistdata.add(
//                        CelebsModel(
//                            celebsobject.optString("name"),
//                            celebsobject.optString("photo"),
//                            celebsobject.optString("gender")
//                        )
//                    )
//                }
//            }
//        }
//        celebsdetails.value = celebslistdata
//    }
//
//
//    fun assetJSONFile(filename: String?, context: Context): String {
//        val manager: AssetManager = context.assets
//        val file: InputStream = manager.open(filename!!)
//        val formArray = ByteArray(file.available())
//        file.read(formArray)
//        file.close()
//        return String(formArray)
//    }

//static data
//    private fun getCelebsDetails() {
//        val celebslist = arrayListOf(
//            CelebsModel(
//                "Kriti Sanon",
//                "https://static.india.com/wp-content/uploads/2017/04/Kriti-Sanon-415x246.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Disha Patani",
//                "https://m.media-amazon.com/images/M/MV5BZmM3YTg2ZjUtNWMwMi00NWE2LWE1YmMtYWM1ZDViNmYzOWRkXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_UX140_CR0,0,140,209_AL_.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Tamannaah Bhatia",
//                "https://assets.architecturaldigest.in/photos/60812a76d327741a9504d3ae/2:3/w_719,h_1079,c_limit/Inside%20Tamannaah%20Bhatia's%20Mumbai%20home.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Sunny Leone",
//                "https://m.media-amazon.com/images/M/MV5BMTM2Mzc1MDYxOV5BMl5BanBnXkFtZTcwMzc1MDQ4Nw@@._V1_UY209_CR9,0,140,209_AL_.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Diana Penty",
//                "https://m.media-amazon.com/images/M/MV5BODUzMjU1NTk4M15BMl5BanBnXkFtZTcwNjU2MTcxOA@@._V1_UY209_CR12,0,140,209_AL_.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Pooja Hegde",
//                "https://upload.wikimedia.org/wikipedia/commons/e/ee/Pooja_Hegde_at_Ala_Vaikunthapurramuloo_reunion.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Yami Gautam",
//                "https://static.toiimg.com/thumb/msid-83421622,width-1200,height-900,resizemode-4/.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Rakul Preet Singh ",
//                "https://www.tollywood.net/wp-content/uploads/2020/12/Rakul-Preet-Singh-Sucker-for-love-and-believer-of-marriage.gif",
//                "Female"
//            ),
//            CelebsModel(
//                "Shruti Haasan",
//                "https://images.indulgexpress.com/uploads/user/imagelibrary/2019/10/9/original/ShrutiHaasan.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Raashi Khanna",
//                "https://www.mrdustbin.com/wp-content/uploads/2020/06/Raashi-Khanna-2.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Shruti Haasan",
//                "https://jbt-en-images.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/09123840/shruti-hasan-south-indian-beautiful-actress.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Rashmika Mandanna",
//                "https://jbt-en-images.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/09123815/Rashmika-Mandana-south-indian-beautiful-actress.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Kajal Aggarwal",
//                "https://jbt-en-images.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/09123719/kajal-Aggarwal-south-indian-beautiful-actress.jpg",
//                "Female"
//            ),
//            CelebsModel(
//                "Rakul Preet Singh",
//                "https://jbt-en-images.s3.ap-south-1.amazonaws.com/wp-content/uploads/2021/09/09123806/Rakul-preet-singh-south-indian-beautiful-actress.jpg",
//                "Female"
//            )
//        )
//        celebsdetails.value = celebslist
//    }

}