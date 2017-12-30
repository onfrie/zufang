//
//        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
//                //设置PopupWindow布局
//                .setView(R.layout.view_flexlayout)
//                //设置宽高
//                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT)
//                //设置动画
//                .setAnimationStyle(R.style.AnimDown)
//                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
//                .setBackGroundLevel(0.5f)
//                //设置PopupWindow里的子View及点击事件
//                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
//                    @Override
//                    public void getChildView(View view, int layoutResId) {
//                        TextView tv_child = (TextView) view.findViewById(R.id.tv_child);
//                        tv_child.setText("我是子View");
//                    }
//                })
//                //设置外部是否可点击 默认是true
//                .setOutsideTouchable(true)
//                //开始构建
//                .create();
//
//
//        View contentView = popupWindow.getContentView();
//        TagFlowLayout flowLayout = contentView.findViewById(R.id.flow_layout);
//        btnCount = (Button) findViewById(R.id.btn_get_count);
//
//        List<String> sourceData = new ArrayList<>();
//        sourceData.add("程序员");
//        sourceData.add("设计师");
//        sourceData.add("产品经理");
//        sourceData.add("运营");
//        sourceData.add("商务");
//        sourceData.add("人事经理");
//        sourceData.add("项目经理");
//        sourceData.add("客户代表");
//        sourceData.add("技术主管");
//        sourceData.add("测试工程师");
//        sourceData.add("前端工程师");
//        sourceData.add("Java工程师");
//        sourceData.add("Android工程师");
//        sourceData.add("iOS工程师");
//
//        List<String> selectItems = new ArrayList<>();
//        selectItems.add("客户代表");
//        selectItems.add("Java工程师");
//
//
//
//        StringTagAdapter adapter = new StringTagAdapter(this, sourceData, selectItems);
//        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
//            @Override
//            public void onSubscribe(List<String> selectedItem) {
//                btnCount.setText("已选择" + selectedItem.size() + "个");
//            }
//        });
//        flowLayout.setAdapter(adapter);
//        btnCount.setText("已选择" + adapter.getSelectedList().size() + "个");
//        findViewById(R.id.btn_switch_data).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                List<String> data = new ArrayList<>();
//                data.add("客户代表");
//                data.add("Java工程师");
//
//                List<String> selectList = new ArrayList<>();
//                selectList.add("客户代表");
//                adapter.setSource(data);
//                adapter.setSelectItems(selectList);
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//
//        popupWindow.showAsDropDown(view);
