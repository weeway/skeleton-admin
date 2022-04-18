<template>
  <Submenu :name="`${parentName}`">
    <template slot="title">
      <CommonIcon :type="parentItem.icon || ''" />
      <span>{{ showTitle(parentItem) }}</span>
    </template>
    <!-- 二级菜单 -->
    <template v-for="item in children">
      <!-- 如果有一个以上的二级菜单 -->
      <template v-if="item.children && item.children.length > 0">
        <!-- 二级菜单展开 -->
        <side-menu-item
          :key="`menu-${item.name}`"
          :parent-item="item"
          v-if="showChildren(item)"
        ></side-menu-item>
        <!-- 二级菜单折叠 -->
        <MenuItem
          :key="`menu-${item.children[0].name}`"
          :name="getNameOrHref(item, true)"
          v-else
        >
          <CommonIcon
            :key="`menu-${item.children[0].name}-common-icon`"
            :type="item.children[0].icon || ''"
          />
          <span :key="`menu-${item.children[0].name}-span`">{{
            showTitle(item.children[0])
          }}</span>
        </MenuItem>
      </template>
      <!-- 如果没有二级菜单 -->
      <template v-else>
        <side-menu-item
          :key="`menu-${item.name}`"
          :parent-item="item"
          v-if="showChildren(item)"
        ></side-menu-item>
        <MenuItem :key="`menu-${item.name}`" :name="getNameOrHref(item)" v-else>
          <CommonIcon
            :key="`menu-${item.name}-common-icon`"
            :type="item.icon || ''"
          />
          <span :key="`menu-${item.name}-span`">{{ showTitle(item) }}</span>
        </MenuItem>
      </template>
    </template>
  </Submenu>
</template>
<script>
import mixin from "./mixin";
import itemMixin from "./item-mixin";
export default {
  name: "SideMenuItem",
  mixins: [mixin, itemMixin],
};
</script>
