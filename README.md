# ProjectFramework
快速搭建项目框架

    binding.tvMsg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              showLoading(false);
              startRequestNetData(service.getUserGet("涂文远", "123456"), new OnRecvDataListener<User>() {
                  @Override
                  public void onRecvData(User o) {
                      Toast.makeText(getContext(),o.toString(),Toast.LENGTH_SHORT).show();
                      switch (rd.nextInt(3)){
                          case 0:
                              showNoNetView();
                              break;
                          case 1:
                              showNoDataView();
                              break;
                          case 2:
                              showErrorView();
                              break;
                      }
                      hideLoding();
                  }
                  @Override
                  public void onError(Exception e) {
                      Toast.makeText(getContext(),"发生错误："+e.getMessage(),Toast.LENGTH_SHORT).show();
                      hideLoding();
                  }
              });

          }
      });
      
#### 效果演示

[img]
