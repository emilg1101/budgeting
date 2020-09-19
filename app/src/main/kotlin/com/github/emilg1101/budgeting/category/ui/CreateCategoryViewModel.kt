package com.github.emilg1101.budgeting.category.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.emilg1101.budgeting.category.domain.CreateCategoryUseCase
import com.github.emilg1101.budgeting.core.base.BaseViewModel
import com.github.emilg1101.budgeting.core.di.scope.FeatureScope
import com.github.emilg1101.budgeting.emojipicker.OnEmojiPickerListener
import com.github.emilg1101.budgeting.emojipicker.model.EmojiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class CreateCategoryViewModel @Inject constructor(
    private val createCategoryUseCase: CreateCategoryUseCase
) : BaseViewModel(), OnEmojiPickerListener {

    val name = MutableLiveData<String>()

    private val _selectedEmoji = MutableLiveData<EmojiModel>()
    val selectedEmoji: LiveData<EmojiModel>
        get() = _selectedEmoji

    private val _isEnabled = MediatorLiveData<Boolean>().apply {
        this.value = false
        addSource(name) { this.value = validate(it, _selectedEmoji.value) }
        addSource(_selectedEmoji) { this.value = validate(name.value, it) }
    }
    val isEnabled: LiveData<Boolean>
        get() = _isEnabled

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            name.value?.let { name ->
                selectedEmoji.value?.emoji?.let { emoji ->
                    createCategoryUseCase(CreateCategoryUseCase.Params(name, emoji))
                }
                launch(Dispatchers.Main) { navigateUp() }
            }
        }
    }

    override fun onEmojiPicked(emoji: EmojiModel) {
        _selectedEmoji.value = emoji
    }

    private fun validate(name: String?, emojiModel: EmojiModel?): Boolean {
        return !name.isNullOrEmpty() && emojiModel != null
    }
}
