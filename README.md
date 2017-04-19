# test_okhttp_post

        RequestBody requestBody = RequestBody.create(mediaType,text3);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .post(requestBody)
                .build();