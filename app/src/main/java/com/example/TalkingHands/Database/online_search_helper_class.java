package com.example.TalkingHands.Database;

import android.net.Uri;
import android.widget.VideoView;

public class online_search_helper_class {

    VideoView videoView;

    public Uri search(String search) {
        //Toast.makeText(Context, "we are in uri search", LENGTH_SHORT).show();
        if (search.equals("ا") || search.equals("الف")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F1%20alif.mp4?alt=media&token=660aa2f1-eedb-4cbb-b6a4-16630f49c2c4");
        } else if (search.equals("ب")|| search.equals("بے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F2%20bay.mp4?alt=media&token=c672c10a-dc7b-41ad-9a0a-bba62a77eb34");
        } else if (search.equals("پ")|| search.equals("پے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F3%20pay.mp4?alt=media&token=640214c5-9f56-4e5f-8fde-2cecb42574e3");
        }else if (search.equals("ت")|| search.equals("تے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F4%20tay.mp4?alt=media&token=f8d5f255-61a8-4f0e-b028-73925a8f5a36");
        }else if (search.equals("ٹ")|| search.equals("ٹے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F5%20ty.mp4?alt=media&token=dc081316-4a21-4890-9167-df62d6849955");
        }else if (search.equals("ث")|| search.equals("ثے")){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F6%20say.mp4?alt=media&token=55fcd696-4571-439d-b484-6f613042e460");
        }else if (search.equals("ج")|| search.equals("جيم")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F7%20geem.mp4?alt=media&token=e3fb1349-0b60-4084-9afe-fbd0e35b5f90");
        }else if (search.equals("چ")|| search.equals("چے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F8%20chy.mp4?alt=media&token=fa837fab-02d6-4a03-899b-5cd2099f5f31");
        }else if (search.equals("ح")|| search.equals("حے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F9%20hy.mp4?alt=media&token=664aa193-e132-4dde-a8d0-ba0ef537dbfb");
        }else if (search.equals("خ")|| search.equals("خے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F10%20khy.mp4?alt=media&token=1758ac3a-c326-46a4-b517-39e8fccd306f");
        }else if (search.equals("د")|| search.equals("دال") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F11%20dal.mp4?alt=media&token=31713fb3-8f6e-4be7-a37a-f8c23239cba2");
        }else if (search.equals("ڈ")|| search.equals("ڈال") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F12%20dhal.mp4?alt=media&token=7d391c50-a0e4-4be2-8bea-84442b090411");
        }else if (search.equals("ذ")|| search.equals("ذال") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F13%20zhal.mp4?alt=media&token=6dd524d7-7124-4dda-8637-e1124a3a2066");
        }else if (search.equals("ر")|| search.equals("رے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F14%20ray.mp4?alt=media&token=ff6299fe-0aef-42a3-a285-afa0b318c90c");
        }else if (search.equals("ڑ")|| search.equals("ڑے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F15%20rhay.mp4?alt=media&token=04d89079-9a5d-40dc-a2d7-d2975b9f1a5e");
        }else if (search.equals("ز")|| search.equals("زے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F16%20zay.mp4?alt=media&token=51e4d30d-fb74-43a9-9c22-99ab31240aae");
        }else if (search.equals("ژ")|| search.equals("ژے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F17%20zay.mp4?alt=media&token=120314e6-997b-4be5-ad1f-689c8fa2b8cd");
        }else if (search.equals("س")|| search.equals("سین")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F18%20seen.mp4?alt=media&token=4401b2e2-c8fc-40ea-aa07-c8e183de42a1");
        }else if (search.equals("ش")|| search.equals("شین") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F19%20sheen.mp4?alt=media&token=9fa8f317-6a11-4258-a3a3-ddffb9e0f929");
        }else if (search.equals("ص")|| search.equals("صاد") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F20%20swad.mp4?alt=media&token=60bd7bd4-63fa-4f06-8263-b6fdb2691277");
        }else if (search.equals("ض")|| search.equals("ضاد") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F22%20zwad.mp4?alt=media&token=da7f1687-ae66-4624-a9d6-2ce665d2b544");
        }else if (search.equals("ط")|| search.equals("طوے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F22%20toen.mp4?alt=media&token=f1a022d5-5184-4110-8d33-791a762da2e4");
        }else if (search.equals("ظ")|| search.equals("ظوے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F23%20zoen.mp4?alt=media&token=7e892797-366a-410e-8717-6e5284fbd665");
        }else if (search.equals("ع")|| search.equals("عین") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F24%20aaen.mp4?alt=media&token=9d7effd7-d439-49a1-a7b8-3a046d2e20a9");
        }else if (search.equals("غ")|| search.equals("غین") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F25%20gaeen.mp4?alt=media&token=70659832-2f86-45cc-94d0-6389c4805592");
        }else if (search.equals("ف")|| search.equals("فے") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F26%20fay.mp4?alt=media&token=d5709b40-c795-4fe0-abf7-5cfe5c3566cb");
        }else if (search.equals("ق")|| search.equals("قاف") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F27%20kafh.mp4?alt=media&token=a7fe7302-6ec4-4e16-bfe0-b706646656fb");
        }else if (search.equals("ک")|| search.equals("کاف") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F27%20kafh.mp4?alt=media&token=a7fe7302-6ec4-4e16-bfe0-b706646656fb");
        }else if (search.equals("گ")|| search.equals("گاف") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F28%20ghaf.mp4?alt=media&token=476bca1b-6676-4708-b01a-05fe9c692e3e");
        }else if (search.equals("ل")|| search.equals("لام") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F29%20laam.mp4?alt=media&token=5709f25c-1ab0-4ee4-85ef-521c55fc207d");
        }else if (search.equals("م")|| search.equals("میم") ){
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F30%20meem.mp4?alt=media&token=6d1ed1ee-c68c-4906-a2f2-b908c68fa6d7");
        }else if (search.equals("ن")|| search.equals("نون") ) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F31%20noon.mp4?alt=media&token=86b2f9d9-0c6f-48dd-82bb-03c8d0bade5d");
        }else if (search.equals("ں")|| search.equals("نون غنّہ")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F3%20pay.mp4?alt=media&token=640214c5-9f56-4e5f-8fde-2cecb42574e3");
        }else if (search.equals("و")|| search.equals("واؤ")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F32%20wao.mp4?alt=media&token=fdc7c143-db60-4476-ac21-69ae5cc32492");
        }else if (search.equals("ہ")|| search.equals("گول ہے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F33%20hy.mp4?alt=media&token=726233de-68ec-4c8a-abb7-9017f9582695");
        }else if (search.equals("ھ")|| search.equals("دو چشمی ہے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F34%20hamza.mp4?alt=media&token=08076542-a904-4f2a-8767-e59503d69fc7");
        }else if (search.equals("ی")|| search.equals("چھوٹی يے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F35%20ya.mp4?alt=media&token=083253e6-8d60-43db-aca6-09f6df5e8109");
        }else if (search.equals("ے")|| search.equals("بڑی يے")) {
            return Uri.parse("https://firebasestorage.googleapis.com/v0/b/fir-d13e1.appspot.com/o/Alphabet%2F36%20ya.mp4?alt=media&token=3f91fab0-9edf-4c75-bad0-f3fb1c035970");
        } else
            return null;
    }
}
